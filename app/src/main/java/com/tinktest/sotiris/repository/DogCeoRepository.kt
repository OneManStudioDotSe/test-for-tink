package se.onemanstudio.test.tink.repository

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import se.onemanstudio.test.tink.BuildConfig
import se.onemanstudio.test.tink.network.DogCeoService
import se.onemanstudio.test.tink.repository.dtos.DogCeoResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object DogCeoRepository {
    private val contentAPI: DogCeoService

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
        this.baseUrl(BuildConfig.BASE_URL_FOR_DOGCEO)
        this.client(client)
    }.build()

    init {
        contentAPI = retrofit.create(DogCeoService::class.java)
    }

    suspend fun getDoggos(): DogCeoResponse? {
        return contentAPI.getPugsFromDogCeo()
    }
}
