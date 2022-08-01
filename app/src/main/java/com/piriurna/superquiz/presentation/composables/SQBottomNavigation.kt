package com.piriurna.superquiz.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.piriurna.superquiz.presentation.BottomNavigationItem
import com.piriurna.superquiz.ui.theme.purple

@Composable
fun SQBottomNavigation(
    modifier: Modifier = Modifier,
    selectedColor : Color,
    unselectedColor: Color,
    items: List<BottomNavigationItem>,
    selectedRoute : String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        items.forEach { item ->
            SQNavigationItem(
                icon = item.icon,
                text = item.title,
                selected = selectedRoute == item.route,
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                onClick = {}
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SQBottomNavigationPreview() {
    val selected = BottomNavigationItem.Profile.route

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        SQBottomNavigation(
            selectedColor = purple,
            unselectedColor = Color.LightGray,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            items = BottomNavigationItem.mockBottomNavigationItems,
            selectedRoute = selected
        )
    }
}