package com.nitroxis.app.quranresearch.Utils

import retrofit2.Response
import retrofit2.http.*

public interface ApiCalls {
    @POST("/search")
    @Headers("Authorization: 2baf6be6ea436a0682be6b57a18f36db943e3bf6130885cc7aa8ea546bd3deca6119144710ce67253cf025c4e8580b78361eceaa668fb5dd5155947a2681b5c9")
    suspend fun search(@Body params: Model.AyaSearchBody): Response<Model.AyaSearchResult>
}