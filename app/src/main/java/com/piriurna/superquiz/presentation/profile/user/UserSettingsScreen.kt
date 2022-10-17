package com.piriurna.superquiz.presentation.profile.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.piriurna.common.composables.button.SQSlider
import com.piriurna.common.composables.button.SQSwipeToConfirmButton
import com.piriurna.common.composables.scaffold.SQScaffold
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.composables.toolbar.AppBarOptions
import com.piriurna.common.theme.SQStyle.TextLato22
import com.piriurna.common.theme.lightPurple
import com.piriurna.superquiz.presentation.profile.ProfileSettingsEvents
import com.piriurna.superquiz.presentation.profile.ProfileSettingsState
import com.piriurna.superquiz.presentation.profile.ProfileSettingsViewModel

@Composable
fun UserSettingsScreen(
    navController: NavHostController
) {
    val viewModel : ProfileSettingsViewModel = hiltViewModel()

    val state = viewModel.state.value


    BuildUserSettingsScreen(state = state, events = viewModel::onTriggerEvent, navController = navController)
}

@Composable
fun BuildUserSettingsScreen(
    navController: NavHostController,
    state: ProfileSettingsState,
    events : (ProfileSettingsEvents) -> Unit
) {

    var username by remember {
        mutableStateOf(state.profileSettings.userName)
    }
    SQScaffold(
        isLoading = state.isLoading,
        appBarOptions = AppBarOptions.AppBarWithTitleAndBack(appBarTitle = "App Settings", {
            events.invoke(ProfileSettingsEvents.SaveUserName(username))
            navController.popBackStack()
        }),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(lightPurple),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(top = AppBarOptions.APP_BAR_HEIGHT),
                horizontalAlignment = Alignment.Start
            ) {

                SQText(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                    text = "User",
                    style = TextLato22
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            value = username,
                            onValueChange = {
                                username = it
                            },
                            label = {
                                SQText(text = "Name")
                            },
                            placeholder = {
                                SQText(text = "Your name here")
                            }
                        )

                        SQSlider(
                            title = "Número de perguntas por categoria",
                            titleAlignment = Alignment.Start,
                            sliderInitialPosition = state.profileSettings.numberOfQuestions.toFloat()
                        )

                    }


                }

                SQText(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                        .padding(top = 16.dp),
                    text = "App Information",
                    style = TextLato22
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    val buttonHorizontalPadding = 24.dp
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(buttonHorizontalPadding),
                    ) {
                        SQSwipeToConfirmButton(
                            modifier = Modifier.fillMaxWidth(),
                            onComplete = { },
                            buttonText = "REMOVE ALL DATA"
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserSettingsScreenPreview() {
    BuildUserSettingsScreen(
        rememberNavController(),
        state = ProfileSettingsState(),
        events = {}
    )
}