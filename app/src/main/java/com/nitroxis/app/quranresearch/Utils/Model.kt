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

    val language = arrayListOf(
        "1" to "الفاتحة <Al-Faatiha>",
        "2" to "البقرة <Al-Baqara>",
        "3" to "آل عمران <Aal-i-Imraan>",
        "4" to "النساء <An-Nisaa>",
        "5" to "المائدة <Al-Maaida>",
        "6" to "الأنعام <Al-An'aam>",
        "7" to "الأعراف <Al-A'raaf>",
        "8" to "الأنفال <Al-Anfaal>",
        "9" to "التوبة <At-Tawba>",
        "10" to "يونس <Yunus>",
        "11" to "هود <Hud>",
        "12" to "يوسف <Yusuf>",
        "13" to "الرعد <Ar-Ra'd>",
        "14" to "ابراهيم <Ibrahim>",
        "15" to "الحجر <Al-Hijr>",
        "16" to "النحل <An-Nahl>",
        "17" to "الإسراء <Al-Israa>",
        "18" to "الكهف <Al-Kahf>",
        "19" to "مريم <Maryam>",
        "20" to "طه <Taa-Haa>",
        "21" to "الأنبياء <Al-Anbiyaa>",
        "22" to "الحج <Al-Hajj>",
        "23" to "المؤمنون <Al-Muminoon>",
        "24" to "النور <An-Noor>",
        "25" to "الفرقان <Al-Furqaan>",
        "26" to "الشعراء <Ash-Shu'araa>",
        "27" to "النمل <An-Naml>",
        "28" to "القصص <Al-Qasas>",
        "29" to "العنكبوت <Al-Ankaboot>",
        "30" to "الروم <Ar-Room>",
        "31" to "لقمان <Luqman>",
        "32" to "السجدة <As-Sajda>",
        "33" to "الأحزاب <Al-Ahzaab>",
        "34" to "سبإ <Saba>",
        "35" to "فاطر <Faatir>",
        "36" to "يس <Yaseen>",
        "37" to "الصافات <As-Saaffaat>",
        "38" to "ص <Saad>",
        "39" to "الزمر <Az-Zumar>",
        "40" to "غافر <Al-Ghaafir>",
        "41" to "فصلت <Fussilat>",
        "42" to "الشورى <Ash-Shura>",
        "43" to "الزخرف <Az-Zukhruf>",
        "44" to "الدخان <Ad-Dukhaan>",
        "45" to "الجاثية <Al-Jaathiya>",
        "46" to "الأحقاف <Al-Ahqaf>",
        "47" to "محمد <Muhammad>",
        "48" to "الفتح <Al-Fath>",
        "49" to "الحجرات <Al-Hujuraat>",
        "50" to "ق <Qaaf>",
        "51" to "الذاريات <Adh-Dhaariyat>",
        "52" to "الطور <At-Tur>",
        "53" to "النجم <An-Najm>",
        "54" to "القمر <Al-Qamar>",
        "55" to "الرحمن <Ar-Rahmaan>",
        "56" to "الواقعة <Al-Waaqia>",
        "57" to "الحديد <Al-Hadid>",
        "58" to "المجادلة <Al-Mujaadila>",
        "59" to "الحشر <Al-Hashr>",
        "60" to "الممتحنة <Al-Mumtahana>",
        "61" to "الصف <As-Saff>",
        "62" to "الجمعة <Al-Jumu'a>",
        "63" to "المنافقون <Al-Munaafiqoon>",
        "64" to "التغابن <At-Taghaabun>",
        "65" to "الطلاق <At-Talaaq>",
        "66" to "التحريم <At-Tahrim>",
        "67" to "الملك <Al-Mulk>",
        "68" to "القلم <Al-Qalam>",
        "69" to "الحاقة <Al-Haaqqa>",
        "70" to "المعارج <Al-Ma'aarij>",
        "71" to "نوح <Nooh>",
        "72" to "الجن <Al-Jinn>",
        "73" to "المزمل <Al-Muzzammil>",
        "74" to "المدثر <Al-Muddaththir>",
        "75" to "القيامة <Al-Qiyaama>",
        "76" to "الانسان <Al-Insaan>",
        "77" to "المرسلات <Al-Mursalaat>",
        "78" to "النبإ <An-Naba>",
        "79" to "النازعات <An-Naazi'aat>",
        "80" to "عبس <Abasa>",
        "81" to "التكوير <At-Takwir>",
        "82" to "الإنفطار <Al-Infitaar>",
        "83" to "المطففين <Al-Mutaffifin>",
        "84" to "الإنشقاق <Al-Inshiqaaq>",
        "85" to "البروج <Al-Burooj>",
        "86" to "الطارق <At-Taariq>",
        "87" to "الأعلى <Al-A'laa>",
        "88" to "الغاشية <Al-Ghaashiya>",
        "89" to "الفجر <Al-Fajr>",
        "90" to "البلد <Al-Balad>",
        "91" to "الشمس <Ash-Shams>",
        "92" to "الليل <Al-Lail>",
        "93" to "الضحى <Ad-Dhuhaa>",
        "94" to "الشرح <Ash-Sharh>",
        "95" to "التين <At-Tin>",
        "96" to "العلق <Al-Alaq>",
        "97" to "القدر <Al-Qadr>",
        "98" to "البينة <Al-Bayyina>",
        "99" to "الزلزلة <Az-Zalzala>",
        "100" to "العاديات <Al-Aadiyaat>",
        "101" to "القارعة <Al-Qaari'a>",
        "102" to "التكاثر <At-Takaathur>",
        "103" to "العصر <Al-Asr>",
        "104" to "الهمزة <Al-Humaza>",
        "105" to "الفيل <Al-Fil>",
        "106" to "قريش <Quraish>",
        "107" to "الماعون <Al-Maa'un>",
        "108" to "الكوثر <Al-Kawthar>",
        "109" to "الكافرون <Al-Kaafiroon>",
        "110" to "النصر <An-Nasr>",
        "111" to "المسد <Al-Masad>",
        "112" to "الإخلاص <Al-Ikhlaas>",
        "113" to "الفلق <Al-Falaq>",
        "114" to "الناس <An-Naas>"
    )
    data class AyaSearchBody(
        var q: String = "",
        var lang: String="en",
        var limit: Int = 10,
        var page: Int = 1,
        var sura: String= "",
        var ayaFrom: Int = 1,
        var ayaTo: Int = 114,
        var origin: String? = null,
        var type: String? = null,
        var edition: String? = null
    )


    data class AyaSearchResult(
        val total:Int = 0,
        val limit:Int = 0,
        val page:Int = 0,
        val pages:Int = 0,
        val ayas: ArrayList<AyaObject>
    )


}