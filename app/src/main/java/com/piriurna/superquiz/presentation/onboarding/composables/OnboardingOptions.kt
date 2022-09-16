package com.piriurna.superquiz.presentation.onboarding.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.piriurna.common.composables.button.SQButton
import com.piriurna.common.composables.button.SQRoundButton
import com.piriurna.common.composables.text.SQText
import com.piriurna.superquiz.presentation.onboarding.models.OnboardingUI

@Composable
fun OnboardingOptions(
    onboardingPage: OnboardingUI,
    isLastPage : Boolean,
    onSkipClick: () -> Unit,
    onNextClick: () -> Unit,
    onFinishClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        if(isLastPage) {
            OnboardingStartOption(onboardingPage, onFinishClick = onFinishClick)
        } else {
            OnboardingSkipOption(onboardingPage, onSkipClick = onSkipClick, onNextClick = onNextClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingOptionsPreview() {
    OnboardingOptions(OnboardingUI.getOnboardingMockList[0], true, {}, {}, {})
}

@Composable
fun OnboardingSkipOption(
    pageModel: OnboardingUI,
    onSkipClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment= Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextButton(onClick = onSkipClick) {
            SQText(
                text = "Skip now",
                color = Color.Black,
            )
        }
        
        SQRoundButton(
            imageColor = pageModel.primaryColor,
            buttonColor = pageModel.primaryColor,
            onClickListener = onNextClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingSkipOptionPreview() {
    OnboardingSkipOption(
        pageModel = OnboardingUI.getOnboardingMockList[0],
        onSkipClick = {},
        onNextClick = {}
    )
}

@Composable
fun OnboardingStartOption(
    onboardingPage: OnboardingUI,
    onFinishClick: () -> Unit
) {
    SQButton(
        onClick = onFinishClick,
        buttonText = "Get Started",
        backgroundColor = onboardingPage.primaryColor,
    )
}

@Preview(showBackground = true)
@Composable
private fun OnboardingStartOptionPreview() {
    OnboardingStartOption(OnboardingUI.getOnboardingMockList[2], {})
}

