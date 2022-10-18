package com.piriurna.superquiz.presentation.splash

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
import com.piriurna.domain.models.splash.SplashDestination
import com.piriurna.superquiz.presentation.AppLogo
import com.piriurna.superquiz.presentation.navigation.RootDestinationScreen

@Composable
fun SplashScreen(
    navController: NavHostController
) {
    val splashViewModel : SplashViewModel = hiltViewModel()

    BuildSplashScreen(splashViewModel.state.value, navController)

}

@Composable
fun BuildSplashScreen(
    state : SplashState,
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
        AppLogo(
            shouldAnimate = state.error == null
        )
    }
}

@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
private fun SplashScreenPreview() {

    val state by remember {
        mutableStateOf(SplashState(error = "Error connecting to the network"))
    }

    Box() {
        BuildSplashScreen(
            state = state,
            navController = rememberNavController()
        )
    }



}