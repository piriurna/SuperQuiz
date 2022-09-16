package com.piriurna.superquiz.presentation.profile.user

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.theme.SQStyle
import com.piriurna.superquiz.presentation.profile.ProfileSettingsViewModel
import com.piriurna.superquiz.ui.theme.purple

@Composable
fun UserSettingsScreen() {
    val viewModel : ProfileSettingsViewModel = hiltViewModel()

    val state = viewModel.state.value

    var userNameValue by remember {
        mutableStateOf(state.profileSettings.userName)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 64.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SQText(text = "User Data", style = SQStyle.TextLato35)

            TextField(
                modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
                value = userNameValue,
                onValueChange = { userNameValue = it },
                label = {
                    SQText(text = "Name")
                }
            )
        }

        Button(
            modifier = Modifier.fillMaxWidth().height(48.dp),
            onClick = {
                viewModel.triggerSaveUserName(userName = userNameValue)
            }
        ) {
            SQText(text = "Save", color = Color.White)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun UserSettingsScreenPreview() {
    UserSettingsScreen()
}