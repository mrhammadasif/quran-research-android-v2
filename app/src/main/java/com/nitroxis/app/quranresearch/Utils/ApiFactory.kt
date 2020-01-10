package com.nitroxis.app.quranresearch.Utils

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiFactory(val context: Context){
  private fun retrofit(): Retrofit = Retrofit.Builder()
    .client(
      OkHttpClient()
        .newBuilder()
        .build()
    )
    .baseUrl("https://quran.nitroxis.com")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

  val myApi: ApiCalls = retrofit().create(ApiCalls::class.java)


}
