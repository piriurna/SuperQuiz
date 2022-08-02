package com.piriurna.superquiz.di

import com.piriurna.data.remote.TriviaApi
import com.piriurna.data.remote.sources.TriviaApiSource
import com.piriurna.superquiz.ApiConstants.BASE_URL
import com.piriurna.superquiz.ApiConstants.CONNECT_TIMEOUT
import com.piriurna.superquiz.ApiConstants.READ_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideHttpClient() : OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)


        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }


    @Provides
    @Singleton
    fun provideTriviaApi(okHttpClient: OkHttpClient): TriviaApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TriviaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTriviaApiSource(triviaApi: TriviaApi): TriviaApiSource {
        return TriviaApiSource(triviaApi)
    }

}
