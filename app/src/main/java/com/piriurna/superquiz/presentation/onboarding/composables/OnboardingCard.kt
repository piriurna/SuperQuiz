package com.piriurna.superquiz.presentation.onboarding.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piriurna.superquiz.presentation.onboarding.models.OnboardingPageModel
import com.piriurna.superquiz.ui.theme.lightPurple
import com.piriurna.superquiz.ui.theme.orange
import com.piriurna.superquiz.ui.theme.purple

@Composable
fun OnboardingCard(
    onboardingPageModel: OnboardingPageModel
) {
    val pageCount = 6
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(16.dp),

    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                SQViewPagerIndicator(
                    totalDots = pageCount,
                    selectedIndex = onboardingPageModel.pageIndex,
                    selectedColor = onboardingPageModel.primaryColor,
                    unSelectedColor = onboardingPageModel.backgroundColor

                )
            }

            Text(
                text = onboardingPageModel.pageTitle,
                fontSize = 24.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
            
            Text(
                text = onboardingPageModel.pageDescription,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 36.dp),
                color = Color.Gray
            )

            OnboardingOptions(
                isFinalPage = onboardingPageModel.pageIndex == pageCount - 1,
                primaryColor = onboardingPageModel.primaryColor,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun OnboardingCardPreview() {
    val model = OnboardingPageModel(
        pageTitle = "Hmmmm, Healthy food",
        pageDescription = "A variety of healthy foods made by the best chefs, ingredients are easy to find, all delicious flavours can only be found at cookbunda",
        primaryColor = orange,
        backgroundColor = lightPurple,
        pageIndex = 2
    )
    Box(modifier = Modifier.fillMaxSize()) {
        OnboardingCard(model)

    }
}