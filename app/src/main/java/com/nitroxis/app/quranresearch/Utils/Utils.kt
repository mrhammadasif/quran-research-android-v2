package com.nitroxis.app.quranresearch.Utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(json: String) =
    this.fromJson<T>(json, object : TypeToken<T>() {}.type)

