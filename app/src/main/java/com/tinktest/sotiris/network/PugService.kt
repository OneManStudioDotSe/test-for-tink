package com.tinktest.sotiris.network

import com.tinktest.sotiris.models.PugsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PugService {
    @GET("bomb")
    suspend fun getPugs(@Path("count") count: Int): PugsResponse
}