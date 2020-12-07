package com.tinktest.sotiris.repository

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tinktest.sotiris.BuildConfig
import com.tinktest.sotiris.network.DogFactsService
import com.tinktest.sotiris.repository.dtos.DogFactResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object DogFactRepository {
    private val contentAPI: DogFactsService

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BASIC
    }

    private val client: OkHttpClient = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
    }.build()

    private val moshi = Moshi.Builder().apply {
        this.add(KotlinJsonAdapterFactory())
    }.build()

    private val retrofit = Retrofit.Builder().apply {
        this.addConverterFactory(MoshiConverterFactory.create(moshi))
        this.baseUrl(BuildConfig.BASE_URL_FOR_DOGFACTS)
        this.client(client)
    }.build()

    init {
        contentAPI = retrofit.create(DogFactsService::class.java)
    }

    suspend fun getDogFact(): DogFactResponse? {
        return contentAPI.getDogFact()
    }
}
