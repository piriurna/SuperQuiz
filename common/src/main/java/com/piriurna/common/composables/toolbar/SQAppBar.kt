package com.piriurna.common.composables.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.composables.button.SQAppBarIcon
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.theme.SQStyle.TextLato22
import com.piriurna.common.theme.incompleteGray

/**
 * @param backIcon should typically be an [SQAppBarIcon], using an icon from
 * [androidx.compose.material.icons.Icons]
 *
 * @param optionsIcon should typically be an [SQAppBarIcon], using an icon from
 * [androidx.compose.material.icons.Icons]
 */
@Composable
fun SQAppBar(
    modifier: Modifier = Modifier,
    appBarOptions: AppBarOptions,
    backgroundColor : Color = Color.Transparent
) {
    val backButton = if(appBarOptions is BackAppbar) appBarOptions.getBackButton() else null
    val title = if(appBarOptions is TitleAppbar) appBarOptions.getTitle() else null
    val optionsButton = if(appBarOptions is OptionsAppbar) appBarOptions.getOptionsButton() else null

    Box(modifier = modifier
        .background(color = backgroundColor)
        .height(AppBarOptions.APP_BAR_HEIGHT)
        .padding(10.dp)
        .fillMaxWidth()
    ) {
        if(backButton != null) {
            Box(modifier = Modifier.align(Alignment.CenterStart)) {
                backButton()
            }
        }

        if(title != null){
            SQText(modifier = Modifier.align(Alignment.Center), text =  title, style = TextLato22)
        }


        if(optionsButton != null) {
            Box(
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                optionsButton()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SQAppBarPreview() {
    Column(
        Modifier
            .fillMaxWidth()
            .background(color = incompleteGray),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SQAppBar(
            appBarOptions = AppBarOptions.AppBarWithTitleBackAndOptions(
                appBarTitle = "This is the Appbar Title",
                appBarBackButton = {
                    SQAppBarIcon(onClick = { }, icon = Icons.Default.ArrowBack)
               },
                appBarOptionsButton = {
                    SQAppBarIcon(onClick = { }, icon = Icons.Default.Menu)
                },
                onBackPressed = {}
            )
        )

        SQAppBar(
            appBarOptions = AppBarOptions.AppBarWithTitleAndBack(
                appBarTitle = "This is the Appbar Title",
                appBarBackButton = {
                    SQAppBarIcon(onClick = { }, icon = Icons.Default.ArrowBack)
                },
                onBackPressed = {}
            )
        )

        SQAppBar(
            appBarOptions = AppBarOptions.AppBarWithBackAndOptions(
                appBarBackButton = {
                    SQAppBarIcon(onClick = { }, icon = Icons.Default.ArrowBack)
                },
                appBarOptionsButton = {
                    SQAppBarIcon(onClick = { }, icon = Icons.Default.Menu)
                },
                onBackPressed = {}
            )
        )
    }
}