package com.piriurna.common.composables.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.composables.button.SQAppBarIcon
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.theme.SQStyle.TextLato22
import com.piriurna.common.theme.incompleteGray

@Composable
fun SQAppBar(
    modifier: Modifier = Modifier,
    backIcon : ImageVector? = Icons.Default.ArrowBack,
    onBackPressed : (() -> Unit)? = null,
    title : String? = null,
    optionsIcon : ImageVector? = null,
    onOptionsPressed : (() -> Unit)? = null
) {
    Box(modifier = modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        if(backIcon != null) {
            SQAppBarIcon(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = { onBackPressed?.invoke() },
                icon = backIcon
            )
        }

        if(title != null){
            SQText(modifier = Modifier.align(Alignment.Center), text =  title, style = TextLato22)
        }


        if(optionsIcon != null) {
            SQAppBarIcon(
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick = { onOptionsPressed?.invoke() },
                icon = optionsIcon
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SQAppBarPreview() {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = incompleteGray)) {
        SQAppBar(
            title = "App Bar Title",
            optionsIcon = Icons.Default.Menu
        )
    }
}