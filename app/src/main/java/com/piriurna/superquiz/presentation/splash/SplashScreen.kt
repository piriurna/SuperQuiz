package com.piriurna.superquiz.presentation.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.piriurna.common.composables.error.SQErrorContainer
import com.piriurna.common.models.SQError
import com.piriurna.domain.models.splash.SplashDestination
import com.piriurna.superquiz.presentation.AppLogo
import com.piriurna.superquiz.presentation.navigation.RootDestinationScreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController
) {
    val splashViewModel : SplashViewModel = hiltViewModel()

    BuildSplashScreen(splashViewModel.state.value, splashViewModel::onTriggerEvent, navController)

}

@Composable
fun BuildSplashScreen(
    state : SplashState,
    events : (SplashEvents) -> Unit = {},
    navController : NavController
) {
    LaunchedEffect(key1 = state) {
        when(state.destination) {
            SplashDestination.HOME -> {
                navController.popBackStack()
                navController.navigate(RootDestinationScreen.Home.route)
            }

            SplashDestination.ONBOARDING -> {
                navController.popBackStack()
                navController.navigate(RootDestinationScreen.Onboarding.route)
            }

            else -> {}
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {


        AnimatedVisibility(
            visible = state.isLoading || state.error == null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            AppLogo()
        }


        AnimatedVisibility(
            visible = state.error != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            state.error?.let { error ->
                SQErrorContainer(
                    imageResource = error.imageResource,
                    title = error.title,
                    subtitle = error.subtitle,
                    buttonOnClick = { events(SplashEvents.Retry) },
                    buttonText = "RETRY",
                    hasButton = error.canRetry
                )
            }
        }


    }
}

@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
private fun SplashScreenPreview() {

    var state by remember {
        mutableStateOf(SplashState())
    }


    LaunchedEffect(key1 = Unit) {
        state = state.copy(
            isLoading = true
        )

        delay(1000)

        state = state.copy(
            isLoading = false,
            error = SQError.GenericError({})
        )
    }

    Box() {
        BuildSplashScreen(
            state = state,
            navController = rememberNavController(),
            events = {
                state = state.copy(
                    isLoading = true,
                    error = null
                )
            }
        )
    }



}