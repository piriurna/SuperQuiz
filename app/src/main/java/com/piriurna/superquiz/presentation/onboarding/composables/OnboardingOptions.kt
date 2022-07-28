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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.superquiz.presentation.onboarding.models.OnboardingPageModel
import com.piriurna.superquiz.ui.theme.lightPurple
import com.piriurna.superquiz.ui.theme.purple

@Composable
fun OnboardingOptions(
    isFinalPage : Boolean,
    primaryColor: Color,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        if(isFinalPage) {
            OnboardingStartOption(primaryColor)
        } else {
            OnboardingSkipOption(primaryColor)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingOptionsPreview() {
    OnboardingOptions(false, purple)
}

@Composable
fun OnboardingSkipOption(primaryColor : Color) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment= Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextButton(onClick = { /*TODO*/ }, ) {
            Text(text = "Skip now", color = Color.Black)
        }
        
        SQRoundButton(primaryColor = primaryColor)
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingSkipOptionPreview() {

}

@Composable
fun OnboardingStartOption(primaryColor: Color) {
    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = primaryColor),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Get Started", color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingStartOptionPreview() {
//    OnboardingStartOption()
}

