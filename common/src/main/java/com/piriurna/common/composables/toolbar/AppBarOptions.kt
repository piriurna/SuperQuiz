package com.piriurna.common.composables.toolbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.piriurna.common.composables.button.SQAppBarIcon

sealed class AppBarOptions {

    class AppBarWithTitle(
        private val appBarTitle: String
    ) : AppBarOptions(), TitleAppbar {

        override fun getTitle() = appBarTitle

    }

    class AppBarWithBack(
        onBackPressed : () -> Unit,
        private val appBarBackButton : @Composable () -> Unit = {
            SQAppBarIcon(onClick = onBackPressed, icon = Icons.Default.ArrowBack)
        }
    ) : AppBarOptions(), BackAppbar {

        override fun getBackButton() = appBarBackButton
    }

    class AppBarWithBackAndOptions(
        onBackPressed : () -> Unit,
        val appBarBackButton : @Composable () -> Unit = {
            SQAppBarIcon(onClick = onBackPressed, icon = Icons.Default.ArrowBack)
        },
        private val appBarOptionsButton : @Composable () -> Unit,
    ) : AppBarOptions(), BackAppbar, OptionsAppbar {
        override fun getBackButton() = appBarBackButton

        override fun getOptionsButton() = appBarOptionsButton

    }

    class AppBarWithTitleAndBack(
        val appBarTitle: String,
        onBackPressed : () -> Unit,
        val appBarBackButton : @Composable () -> Unit = {
            SQAppBarIcon(onClick = onBackPressed, icon = Icons.Default.ArrowBack)
        }
    ) : AppBarOptions(), BackAppbar, TitleAppbar {

        override fun getTitle() = appBarTitle

        override fun getBackButton() = appBarBackButton
    }

    class AppBarWithTitleBackAndOptions(
        private val appBarTitle: String,
        onBackPressed : () -> Unit,
        private val appBarBackButton : @Composable () -> Unit = {
            SQAppBarIcon(onClick = onBackPressed, icon = Icons.Default.ArrowBack)
        },
        private val appBarOptionsButton : @Composable () -> Unit,
    ): AppBarOptions(), BackAppbar, TitleAppbar, OptionsAppbar {
        override fun getBackButton() = appBarBackButton

        override fun getOptionsButton() = appBarOptionsButton

        override fun getTitle() = appBarTitle

    }

    companion object {
        val APP_BAR_HEIGHT = 88.dp
    }
}
