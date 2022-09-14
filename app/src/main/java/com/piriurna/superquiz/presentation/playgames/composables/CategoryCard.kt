package com.piriurna.superquiz.presentation.playgames.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piriurna.common.composables.cards.SQCard
import com.piriurna.common.composables.progress.SQProgressBar
import com.piriurna.common.composables.text.SQText
import com.piriurna.common.theme.SQStyle.TextLatoBold20
import com.piriurna.domain.models.Category
import com.piriurna.superquiz.mappers.getImage

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    category: Category,
    onClick : () -> Unit
) {

    SQCard(
        modifier = modifier
            .padding(all = 8.dp),
        onClick = onClick
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

                SQText(
                    text = category.name,
                    style = TextLatoBold20,
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
            category = Category.mockCategoryList[1],
            onClick = {}
        )
    }
}