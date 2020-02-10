package com.nitroxis.app.quranresearch.Utils

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(json: String) =
    this.fromJson<T>(json, object : TypeToken<T>() {}.type)

fun Context.isNetworkReachable(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
}
