package com.piriurna.superquiz.presentation.profile.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.piriurna.common.composables.scaffold.SQScaffold
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.composables.toolbar.AppBarOptions
import com.piriurna.common.theme.SQStyle
import com.piriurna.common.theme.lightPurple
import com.piriurna.superquiz.presentation.profile.ProfileSettingsEvents
import com.piriurna.superquiz.presentation.profile.ProfileSettingsState
import com.piriurna.superquiz.presentation.profile.ProfileSettingsViewModel
import com.piriurna.superquiz.ui.theme.purple

@Composable
fun UserSettingsScreen() {
    val viewModel : ProfileSettingsViewModel = hiltViewModel()

    val state = viewModel.state.value


    BuildUserSettingsScreen(state = state, events = viewModel::onTriggerEvent)
}

@Composable
fun BuildUserSettingsScreen(
    state: ProfileSettingsState,
    events : (ProfileSettingsEvents) -> Unit
) {
    SQScaffold(
        isLoading = state.isLoading,
        appBarOptions = AppBarOptions.AppBarWithTitleAndBack(appBarTitle = "User Data"),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 64.dp)
                .background(lightPurple),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp),
                    value = state.profileSettings.userName,
                    onValueChange = { events.invoke(ProfileSettingsEvents.SaveUserName(it)) },
                    label = {
                        SQText(text = "Name")
                    },
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserSettingsScreenPreview() {
    UserSettingsScreen()
}