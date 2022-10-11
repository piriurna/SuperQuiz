package com.piriurna.common.composables.toolbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.piriurna.common.composables.button.SQAppBarIcon

sealed class AppBarOptions {

    class AppBarWithTitle(
        private val appBarTitle: String
    ) : AppBarOptions(), TitleAppbar {

        override fun getTitle() = appBarTitle

    }

    class AppBarWithBack(
        private val appBarBackButton : @Composable () -> Unit = {
            SQAppBarIcon(onClick = { }, icon = Icons.Default.ArrowBack)
        }
    ) : AppBarOptions(), BackAppbar {

        override fun getBackButton() = appBarBackButton
    }

    class AppBarWithBackAndOptions(
        val appBarBackButton : @Composable () -> Unit = {
            SQAppBarIcon(onClick = { }, icon = Icons.Default.ArrowBack)
        },
        private val appBarOptionsButton : @Composable () -> Unit,
    ) : AppBarOptions(), BackAppbar, OptionsAppbar {
        override fun getBackButton() = appBarBackButton

        override fun getOptionsButton() = appBarOptionsButton

    }

    class AppBarWithTitleAndBack(
        val appBarTitle: String,
        val appBarBackButton : @Composable () -> Unit = {
            SQAppBarIcon(onClick = { }, icon = Icons.Default.ArrowBack)
        }
    ) : AppBarOptions(), BackAppbar, TitleAppbar {

        override fun getTitle() = appBarTitle

        override fun getBackButton() = appBarBackButton
    }

    class AppBarWithTitleBackAndOptions(
        private val appBarTitle: String,
        private val appBarBackButton : @Composable () -> Unit = {
            SQAppBarIcon(onClick = { }, icon = Icons.Default.ArrowBack)
        },
        private val appBarOptionsButton : @Composable () -> Unit,
    ): AppBarOptions(), BackAppbar, TitleAppbar, OptionsAppbar {
        override fun getBackButton() = appBarBackButton

        override fun getOptionsButton() = appBarOptionsButton

        override fun getTitle() = appBarTitle

    }

}
