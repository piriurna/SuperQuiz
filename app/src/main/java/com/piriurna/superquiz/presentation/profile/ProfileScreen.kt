package com.piriurna.superquiz.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.piriurna.common.composables.selector.SQRow
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.theme.SQStyle
import com.piriurna.common.theme.SQStyle.TextLato35
import com.piriurna.superquiz.presentation.navigation.HomeDestinationScreen

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
                contentDescription = "User photo",
                modifier = Modifier.size(35.dp)
            )

            SQText(text = "Profile", style = TextLato35)
        }

        SQRow(
            modifier = Modifier.padding(top = 32.dp),
            label = "Settings",
            onClick = {
                navController.navigate(HomeDestinationScreen.UserSettings.route)
            }
        )

        SQRow(
            modifier = Modifier.padding(top = 32.dp),
            label = "Questions",
            onClick = {
                navController.navigate(HomeDestinationScreen.QuestionSettings.route)
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