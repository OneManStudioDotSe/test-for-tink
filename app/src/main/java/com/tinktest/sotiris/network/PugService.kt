package se.onemanstudio.test.tink.network

import se.onemanstudio.test.tink.repository.dtos.PugsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PugService {
    @GET("bomb")
    suspend fun getPugs(@Query("count") count: Int): PugsResponse?
}