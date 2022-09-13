package com.piriurna.common.composables.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
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
    backIcon: @Composable () -> Unit,
    title : String? = null,
    optionsIcon: @Composable () -> Unit,
    backgroundColor : Color = Color.Transparent
) {
    Box(modifier = modifier
        .background(color = backgroundColor)
        .height(88.dp)
        .padding(10.dp)
        .fillMaxWidth()
    ) {
        Box(modifier = Modifier.align(Alignment.CenterStart)) {
            backIcon()
        }

        if(title != null){
            SQText(modifier = Modifier.align(Alignment.Center), text =  title, style = TextLato22)
        }


        Box(
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            optionsIcon()
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
            title = "App Bar Title",
            backIcon = {
                SQAppBarIcon(onClick = { }, icon = Icons.Default.Menu)
           },
            optionsIcon = {
                SQAppBarIcon(onClick = { }, icon = Icons.Default.Menu)
            },
        )

        SQAppBar(
            title = "App Bar Title",
            backIcon = {
                SQAppBarIcon(onClick = { }, icon = Icons.Default.Menu)
            },
            optionsIcon = {
            },
        )

        SQAppBar(
            title = "App Bar Title",
            backIcon = {
            },
            optionsIcon = {
            },
        )
    }
}