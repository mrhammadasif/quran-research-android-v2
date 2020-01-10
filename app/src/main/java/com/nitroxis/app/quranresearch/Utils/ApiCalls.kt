package com.nitroxis.app.quranresearch.Utils

import retrofit2.Response
import retrofit2.http.*

public interface ApiCalls {
    @POST("/search")
    suspend fun search(@Body params: Model.AyaSearchBody): Response<Model.AyaSearchResult>
}