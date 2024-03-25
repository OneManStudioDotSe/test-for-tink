package se.onemanstudio.test.tink.network

import se.onemanstudio.test.tink.repository.dtos.DogFactResponse
import retrofit2.http.GET

interface DogFactsService {
    @GET("api/facts")
    suspend fun getDogFact(): DogFactResponse?
}