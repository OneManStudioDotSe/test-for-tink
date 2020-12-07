package com.tinktest.sotiris.network

import com.tinktest.sotiris.repository.dtos.DogFactResponse
import retrofit2.http.GET

interface DogFactsService {
    @GET("api/facts")
    suspend fun getDogFact(): DogFactResponse?
}