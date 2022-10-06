package com.piriurna.domain.usecases

import com.piriurna.domain.models.Question
import com.piriurna.domain.repositories.TriviaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDbCategoryQuestionsUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository
) {

    operator fun invoke(categoryId : Int) : Flow<List<Question>> = triviaRepository.getCategoryQuestionsFromDb(categoryId)
}