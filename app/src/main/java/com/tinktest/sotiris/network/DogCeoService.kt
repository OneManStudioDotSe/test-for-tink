package com.tinktest.sotiris.network

import com.tinktest.sotiris.repository.dtos.DogCeoResponse
import com.tinktest.sotiris.repository.dtos.PugsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DogCeoService {
    @GET("api/breed/pug/images")
    suspend fun getPugsFromDogCeo(): DogCeoResponse?
}