package com.piriurna.domain.usecases

import com.piriurna.domain.ApiNetworkResponse
import com.piriurna.domain.Resource
import com.piriurna.domain.models.Category
import com.piriurna.domain.models.LoadTriviaType
import com.piriurna.domain.repositories.TriviaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadTriviaDataUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository
) {

    operator fun invoke() : Flow<Resource<LoadTriviaType>> = flow {
        emit(Resource.Loading())

        val firstInstall = false
        val shouldFetchNewCategories = true
        if(firstInstall){
            val categoriesResult : ApiNetworkResponse<List<Category>> = triviaRepository.getCategories()

            categoriesResult.data?.let { data ->
                triviaRepository.insertCategoriesInDb(data)
                //TODO: Tratar das perguntas dessa categoria
                emit(Resource.Success(LoadTriviaType.FIRST_INSTALL))
            }?: kotlin.run {
                emit(Resource.Error(message = categoriesResult.error.message!!))
            }
        } else {
            if(shouldFetchNewCategories) {
                val categoriesResult : ApiNetworkResponse<List<Category>> = triviaRepository.getCategories()

                categoriesResult.data?.let { data ->
                    val nonExistingCategories = data.filterNot { apiCategory ->
                        return@filterNot triviaRepository.getDbCategories().any { it.id == apiCategory.id }
                    }

                    if(nonExistingCategories.isNullOrEmpty()) {
                        emit(Resource.Success(LoadTriviaType.NO_CATEGORIES_UPDATED))
                    } else {
                        //TODO: Buscar todas as perguntas destas categorias novas
                        triviaRepository.insertCategoriesInDb(nonExistingCategories)//
                        //TODO: Fazer o insert das perguntas na base de dados
                        emit(Resource.Success(LoadTriviaType.CATEGORIES_UPDATED))
                    }

                }?: kotlin.run {
                    emit(Resource.Error(message = categoriesResult.error.message!!))
                }
            } else {
                if(triviaRepository.getDbCategories().isNullOrEmpty()){
                    val categoriesResult : ApiNetworkResponse<List<Category>> = triviaRepository.getCategories()

                    categoriesResult.data?.let { data ->
                        triviaRepository.insertCategoriesInDb(data)
                        //TODO: Tratar das perguntas dessa categoria
                        emit(Resource.Success(LoadTriviaType.NO_STATE))
                    }?: kotlin.run {
                        emit(Resource.Error(message = categoriesResult.error.message!!))
                    }
                } else {
                    emit(Resource.Success(LoadTriviaType.NO_STATE))
                }
            }
        }
















        //Primeira instalacao da app
        //Ir buscar à api as categorias e suas respectivas perguntas
        //gravar na base de dados essa info
        //enviar sucesso

        //Se houver erro da api
        //enviar erro



        //Restantes aberturas da app
        //Verificar se ja temos dados na base de dados
        //if true
        //enviar sucesso
        //if false
        //seguir o fluxo da primeira instalacao




        //De 3 a 3 dias verificar se temos novas categorias na api
        //if true
        //ir buscar todas as categorias que ainda nao existem na app na base de dados
        //fazer o pedido das perguntas destas categorias novas
        //gravar tudo na base de dados
        //
        //if false
        //enviar sucesso



    }
}