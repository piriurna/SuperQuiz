package com.piriurna.superquiz.presentation.onboarding.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.piriurna.common.composables.button.SQRoundButton
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.theme.SQStyle.TextLatoBold
import com.piriurna.superquiz.presentation.onboarding.models.OnboardingPage

@Composable
fun OnboardingOptions(
    onboardingPage: OnboardingPage,
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
    OnboardingOptions(OnboardingPage.getOnboardingMockList[0], true, {}, {}, {})
}

@Composable
fun OnboardingSkipOption(
    pageModel: OnboardingPage,
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
        pageModel = OnboardingPage.getOnboardingMockList[0],
        onSkipClick = {},
        onNextClick = {}
    )
}

@Composable
fun OnboardingStartOption(
    onboardingPage: OnboardingPage,
    onFinishClick: () -> Unit
) {
    Button(
        onClick = onFinishClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = onboardingPage.primaryColor),
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
    ) {
        SQText(
            text = "Get Started",
            color = Color.White,
            style = TextLatoBold
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingStartOptionPreview() {
    OnboardingStartOption(OnboardingPage.getOnboardingMockList[2], {})
}

