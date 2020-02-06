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
        val q: String = "",
        val lang: String="en",
        val limit: Int = 10,
        val page: Int = 1,
        val sura: Array<String>? = null,
        val ayaFrom: Int = 1,
        val ayaTo: Int = 114,
        val origin: String? = null,
        val type: String? = null,
        val edition: String? = null
    )


    data class AyaSearchResult(
        val total:Int = 0,
        val limit:Int = 0,
        val page:Int = 0,
        val pages:Int = 0,
        val ayas: ArrayList<AyaObject>
    )


}