package com.piriurna.superquiz.presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.piriurna.domain.models.LoadTriviaType
import com.piriurna.superquiz.presentation.AppLogo
import com.piriurna.superquiz.presentation.navigation.RootDestinationScreen

@Composable
fun SplashScreen(
    navController: NavHostController
) {
    val splashViewModel : SplashViewModel = hiltViewModel()

    val state = splashViewModel.state.value


    when(state.loadTriviaState) {
        LoadTriviaType.FIRST_INSTALL,
        LoadTriviaType.NO_CATEGORIES_UPDATED,
        LoadTriviaType.CATEGORIES_UPDATED,
        LoadTriviaType.NO_STATE -> {
            navController.navigate(RootDestinationScreen.Home.route)
        }
        else -> {}
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AppLogo()
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    SplashScreen(rememberNavController())
}