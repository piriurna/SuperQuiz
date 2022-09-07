package com.piriurna.superquiz.presentation.onboarding.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.composables.viewpager.SQViewPagerIndicator
import com.piriurna.common.theme.SQStyle.TextLato
import com.piriurna.common.theme.SQStyle.TextLatoBold24
import com.piriurna.superquiz.presentation.onboarding.models.OnboardingPage

@Composable
fun OnboardingCard(
    onboardingPage: OnboardingPage,
    pageCount: Int,
    selectedPageIndex : Int,
    onSkipClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    onFinishClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(topStartPercent = 30),
        modifier = modifier,
        elevation = 8.dp

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //-----------------
            // ViewPager Indicator
            //-----------------
            SQViewPagerIndicator(
                totalDots = pageCount,
                selectedIndex = selectedPageIndex,
                selectedColor = onboardingPage.primaryColor,
                unSelectedColor = onboardingPage.backgroundColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )


            //-----------------
            // TEXTS CONTAINERS
            //-----------------
            SQText(
                text = onboardingPage.pageTitle,
                style = TextLatoBold24,
                modifier = Modifier.padding(top = 16.dp)
            )

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
            ) {
                SQText(
                    text = onboardingPage.pageDescription,
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    lineHeight = 23.sp,
                    style = TextLato,
                    modifier = Modifier
                        .padding(horizontal = 36.dp)
                        .align(Alignment.Center)
                )
            }

            //-----------------
            // BUTTONS CONTAINER
            //-----------------
            Box(modifier = Modifier
                .height(120.dp)
            ) {
                OnboardingOptions(
                    onboardingPage = onboardingPage,
                    isLastPage = selectedPageIndex == pageCount - 1,
                    onSkipClick= onSkipClick,
                    onNextClick= onNextClick,
                    onFinishClick= onFinishClick,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun OnboardingCardPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        OnboardingCard(
            onboardingPage = OnboardingPage.getOnboardingMockList[0],
            onSkipClick = {},
            onNextClick = {},
            onFinishClick = {},
            pageCount = 3,
            selectedPageIndex = 0
        )

    }
}