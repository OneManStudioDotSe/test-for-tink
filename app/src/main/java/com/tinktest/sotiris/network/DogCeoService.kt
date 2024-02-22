package se.onemanstudio.test.tink.network

import se.onemanstudio.test.tink.repository.dtos.DogCeoResponse
import se.onemanstudio.test.tink.repository.dtos.PugsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DogCeoService {
    @GET("api/breed/pug/images")
    suspend fun getPugsFromDogCeo(): DogCeoResponse?
}