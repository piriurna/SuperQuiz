package com.piriurna.superquiz.presentation.onboarding.composables

import android.annotation.SuppressLint
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
import com.piriurna.superquiz.R
import com.piriurna.superquiz.presentation.onboarding.models.OnboardingPageModel
import com.piriurna.superquiz.ui.theme.lightPurple
import com.piriurna.superquiz.ui.theme.orange
import kotlinx.coroutines.launch

@Composable
fun OnboardingOptions(
    onboardingPageModel: OnboardingPageModel,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        if(onboardingPageModel.isLastPage()) {
            OnboardingStartOption(onboardingPageModel)
        } else {
            OnboardingSkipOption(onboardingPageModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingOptionsPreview() {
    val model = OnboardingPageModel(
        pageTitle = "Hmmmm, Healthy food",
        pageDescription = "A variety of healthy foods made by the best chefs, ingredients are easy to find, all delicious flavours can only be found at cookbunda",
        primaryColor = orange,
        backgroundColor = lightPurple,
        pageIndex = 0,
        nextPage = 1,
        mainImage = R.drawable.ic_banana_svgrepo_com
    )
    OnboardingOptions(model)
}

@Composable
fun OnboardingSkipOption(pageModel: OnboardingPageModel) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment= Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextButton(onClick = { } ) {
            Text(text = "Skip now", color = Color.Black, fontWeight = FontWeight.W400)
        }
        
        SQRoundButton(
            primaryColor = pageModel.primaryColor,
            onClickListener = { goToNextPage(pageModel) } )
    }
}


@SuppressLint("Range")
@OptIn(ExperimentalPagerApi::class)
private fun goToNextPage(pageModel: OnboardingPageModel) {
    pageModel.viewPagerModel?.let {
        with(it){
            scope.launch {
                state.animateScrollToPage(pageModel.nextPage)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingSkipOptionPreview() {

}

@Composable
fun OnboardingStartOption(onboardingPageModel: OnboardingPageModel) {
    Button(
        onClick = { goToNextPage(onboardingPageModel) },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = onboardingPageModel.primaryColor),
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
    ) {
        Text(text = "Get Started", color = Color.White, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingStartOptionPreview() {
//    OnboardingStartOption()
}

