package com.piriurna.common.composables.loading

import androidx.annotation.RawRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.piriurna.common.R
import com.piriurna.common.composables.animations.SQLottieLoading
import com.piriurna.common.theme.secondaryBackground

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SQLoading(
    isLoading: Boolean,
    @RawRes lottieId: Int = R.raw.loading,
) {

    AnimatedVisibility(
        modifier = Modifier.fillMaxSize(),
        visible = isLoading,
        enter = scaleIn(),
        exit = scaleOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(enabled = false, onClick = {})
                .background(MaterialTheme.colors.secondaryBackground.copy(alpha = 0.8f)),
            contentAlignment = Alignment.Center
        ) {
            SQLottieLoading(lottieId= lottieId)
        }
    }


}

@Preview(showBackground = true)
@Composable
private fun SQLoadingPreview() {
    SQLoading(isLoading = true)
}