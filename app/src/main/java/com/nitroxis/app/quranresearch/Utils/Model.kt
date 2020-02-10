package com.nitroxis.app.quranresearch.Utils



class Model {

    data class AyaObject (
        val sura: SuraObject? = null,
        val edition: AyaEdition? = null,
        val meta: AyaMeta? = null,
        val text: String?= null,
        val aya: Int ?= null,
        val id: String? = null,
        val sourceText: String? = null
    )

    data class SuraOrder(
        val revelation: Int,
        val quran: Int
    )
    data class AyaMeta (
        val sajda: String?,
        val juzz: Int,
        val ruku: Int,
        val rubai: Int,
        val manzil: Int
    )
    data class AyaEdition(
        val editionId: Int,
        val language: String,
        val englishName: String,
        val name: String,
        val type: String,
        val source: String,
        val identifier: String
    )

    data class SuraObject(
        val order: SuraOrder,
        val name: String,
        val englishName: String,
        val englishTranslation: String,
        val startingFrom: Int,
        val totalAyaInSurah: Int,
        val rukus: Int,
        val origin: String
    )


    data class AyaSearchBody(
        var q: String = "",
        var lang: String="en",
        var limit: Int = 10,
        var page: Int = 1,
        var sura: String?= null,
        var ayaFrom: Int = 1,
        var ayaTo: Int = 286,
        var origin: String? = null,
        var type: String? = null,
        var edition: String? = null,
        var sajda: String?=null
    )


    data class AyaSearchResult(
        val total:Int = 0,
        val limit:Int = 0,
        val page:Int = 0,
        val pages:Int = 0,
        val ayas: ArrayList<AyaObject>
    )


}