package com.yusuf.app.testtwiscode.di

import com.google.gson.GsonBuilder
import com.yusuf.app.testtwiscode.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
//    https://ranting.twisdev.com/index.php/rest/items/search/api_key/teampsisthebest/

    @Provides
    fun providesBaseUrl(): String {
        return "https://ranting.twisdev.com/index.php/rest/items/search/api_key/"
    }

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor { message ->
            Timber.tag("API_REQUEST").e(message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()
            .callTimeout(40, TimeUnit.SECONDS)
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
        okHttpClient.build()
        return okHttpClient.build()
    }


    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create( GsonBuilder().serializeNulls().create() )
    }

    @Provides
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient, baseUrl: String, converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun provideRestApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
