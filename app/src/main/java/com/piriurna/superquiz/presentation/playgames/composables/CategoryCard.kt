package com.piriurna.superquiz.presentation.playgames.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piriurna.common.composables.cards.SQCard
import com.piriurna.domain.models.Category
import com.piriurna.superquiz.mappers.getImage
import com.piriurna.superquiz.presentation.composables.SQProgressBar
import com.piriurna.superquiz.presentation.composables.models.ProgressIndicatorModel
import com.piriurna.superquiz.presentation.composables.models.ProgressIndicatorText

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    category: Category
) {

    SQCard(
        modifier = modifier
            .padding(all = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 24.dp, top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    painter = painterResource(id = category.getImage()),
                    contentDescription = "CategoryImage",
                    modifier = Modifier
                        .align(Alignment.Start)
                        .size(64.dp)
                )

                Text(
                    text = category.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 28.sp
                )
            }

            val progressIndicatorText = "You completed ${category.completionRate}%"
            SQProgressBar(
                    progress = category.completionRate,
                    percentageText = progressIndicatorText
                )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CategoryCardPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        CategoryCard(
            category = Category.mockCategoryList[1]
        )
    }
}