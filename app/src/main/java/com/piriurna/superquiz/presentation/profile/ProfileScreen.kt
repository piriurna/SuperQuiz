package com.piriurna.superquiz.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.piriurna.common.composables.selector.SQMenuRow
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.theme.SQStyle.TextLato35
import com.piriurna.superquiz.R
import com.piriurna.superquiz.presentation.navigation.HomeDestinationScreen
import com.piriurna.superquiz.presentation.navigation.SettingsDestinations

@Composable
fun ProfileScreen(
    navController: NavController = rememberNavController()
) {
    Column(
        modifier = Modifier
            .padding(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                imageVector = Icons.Default.Face,
                contentDescription = stringResource(R.string.user_photo),
                modifier = Modifier.size(35.dp)
            )

            SQText(text = stringResource(R.string.profile), style = TextLato35)
        }

        SQMenuRow(
            modifier = Modifier.padding(top = 32.dp),
            label = stringResource(R.string.settings),
            onClick = {
                navController.navigate(SettingsDestinations.UserSettings.route)
            }
        )


    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    Box(Modifier.fillMaxSize()) {
        ProfileScreen()
    }
}