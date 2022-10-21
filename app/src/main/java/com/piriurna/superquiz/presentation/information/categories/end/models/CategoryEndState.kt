package com.piriurna.superquiz.presentation.information.categories.end.models

import androidx.compose.ui.res.stringResource
import com.piriurna.common.models.SQError
import com.piriurna.common.theme.errorColor
import com.piriurna.common.theme.primaryGreen
import com.piriurna.domain.models.Category
import com.piriurna.superquiz.R

data class CategoryEndState(
    val isLoading : Boolean = false,
    val category: Category? = null,
    val destination : CategoryEndDestination = CategoryEndDestination.NO_STATE,
    val error : SQError? = null
) {


    fun getImageResource() = if(category?.isSuccess() == true) R.drawable.ic_checked_correct else R.drawable.ic_unchecked_incorrect

    fun getTitleResource() = if(category?.isSuccess() == true) R.string.you_got_percentage_correct else R.string.you_only_got_percentage_correct

    fun getSubtitleResource() = if(category?.isSuccess() == true)
        R.string.now_you_can_load_new_questions_for_the_category_or_go_back_to_the_main_screen
        else R.string.you_can_get_more_questions_to_try_again_or_go_back_to_the_main_screen

    fun getButtonColor() = if(category?.isSuccess() == true) primaryGreen else errorColor
}