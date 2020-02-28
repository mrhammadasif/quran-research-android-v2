package com.nitroxis.app.quranresearch.Utils

object DropDownValues {
    val surah = arrayListOf(
        "" to "---Any One---",
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

    val origin = arrayListOf(
        "" to "---Any One---",
        "Medinan" to "Medinan",
        "Meccan" to "Meccan"
    )
    val sajda = arrayListOf(
        "" to "---Any One---",
        "Inclusive" to "Yes",
        "Exclusive" to "No"
    )
    val editionType = arrayListOf(
        "" to "---Any One---",
        "quran" to "quran",
        "tafsir" to "tafsir",
        "translation" to "translation",
        "transliteration" to "transliteration"
    )

    val lang = arrayListOf(
        "ar" to "Arabic <العربية>",
        "az" to "Azerbaijani <azərbaycan dili>",
        "bg" to "Bulgarian <български език>",
        "bn" to "Bengali <বাংলা>",
        "bs" to "Bosnian <bosanski jezik>",
        "cs" to "Czech <česky, čeština>",
        "de" to "German <Deutsch>",
        "dv" to "Divehi; Dhivehi; Maldivian; <ދިވެހި>",
        "en" to "English <English>",
        "es" to "Spanish; Castilian <español, castellano>",
        "fa" to "Persian <فارسی>",
        "fr" to "French <français, langue française>",
        "ha" to "Hausa <Hausa, هَوُسَ>",
        "hi" to "Hindi <हिन्दी, हिंदी>",
        "id" to "Indonesian <Bahasa Indonesia>",
        "it" to "Italian <Italiano>",
        "ku" to "Kurdish <Kurdî, كوردی‎>",
        "ml" to "Malayalam <മലയാളം>",
        "ms" to "Malay <bahasa Melayu, بهاس ملايو‎>",
        "nl" to "Dutch <Nederlands, Vlaams>",
        "no" to "Norwegian <Norsk>",
        "pl" to "Polish <polski>",
        "pt" to "Portuguese <Português>",
        "ro" to "Romanian, Moldavian, Moldovan <română>",
        "ru" to "Russian <русский язык>",
        "sd" to "Sindhi <सिन्धी, سنڌي، سندھی‎>",
        "so" to "Somali <Soomaaliga, af Soomaali>",
        "sq" to "Albanian <Shqip>",
        "sv" to "Swedish <svenska>",
        "sw" to "Swahili <Kiswahili>",
        "ta" to "Tamil <தமிழ்>",
        "tg" to "Tajik <тоҷикӣ, toğikī, تاجیکی‎>",
        "th" to "Thai <ไทย>",
        "tr" to "Turkish <Türkçe>",
        "tt" to "Tatar <татарча, tatarça, تاتارچا‎>",
        "ug" to "Uighur, Uyghur <Uyƣurqə, ئۇيغۇرچە‎>",
        "ur" to "Urdu <اردو>",
        "uz" to "Uzbek <zbek, Ўзбек, أۇزبېك‎>",
        "zh" to "Chinese <中文 (Zhōngwén), 汉语, 漢語>"
    )
    val edition = arrayListOf(
        "" to "---Any One---",
        "sq.ahmeti" to "Albanian – by Sherif Ahmeti",
        "sq.mehdiu" to "Albanian – by Feti Mehdiu",
        "sq.nahi" to "Albanian – by Hasan Efendi Nahi",
        "ar.muyassar" to "Arabic – by King Fahad Quran Complex",
        "quran-simple" to "Arabic – by Simple",
        "quran-simple-clean" to "Arabic – by Simple Clean",
        "quran-simple-enhanced" to "Arabic – by Simple Enhanced",
        "quran-simple-min" to "Arabic – by Simple Minimal",
        "quran-uthmani-min" to "Arabic – by Uthmani Minimal",
        "quran-uthmani" to "Arabic – by Uthamani",
        "ar.jalalayn" to "Arabic – by Jalal ad-Din al-Mahalli and Jalal ad-Din as-Suyuti",
        "quran-tajweed" to "Arabic – by Tajweed",
        "quran-wordbyword" to "Arabic – by Word By Word",
        "quran-kids" to "Arabic – by Kids",
        "az.mammadaliyev" to "Azerbaijani – by Vasim Mammadaliyev and Ziya Bunyadov",
        "az.musayev" to "Azerbaijani – by Alikhan Musayev",
        "bn.bengali" to "Bengali – by Muhiuddin Khan",
        "bs.mlivo" to "Bosnian – by Mustafa Mlivo",
        "bs.korkut" to "Bosnian – by Besim Korkut",
        "bg.theophanov" to "Bulgarian – by Tzvetan Theophanov",
        "zh.jian" to "Chinese – by Ma Jian",
        "zh.majian" to "Chinese – by Ma Jian",
        "zh.mazhonggang" to "Chinese – by 马仲刚",
        "cs.hrbek" to "Czech – by Preklad I. Hrbek",
        "cs.nykl" to "Czech – by A. R. Nykl",
        "dv.divehi" to "Divehi; Dhivehi; Maldivian; – by Office of the President of Maldives",
        "nl.keyzer" to "Dutch – by Salomo Keyzer",
        "en.ahmedali" to "English – by Ahmed Ali",
        "en.ahmedraza" to "English – by Ahmed Raza Khan",
        "en.arberry" to "English – by A. J. Arberry",
        "en.asad" to "English – by Muhammad Asad",
        "en.daryabadi" to "English – by Abdul Majid Daryabadi",
        "en.hilali" to "English – by Muhammad Taqi-ud-Din al-Hilali and Muhammad Muhsin Khan",
        "en.pickthall" to "English – by Mohammed Marmaduke William Pickthall",
        "en.qaribullah" to "English – by Hasan al-Fatih Qaribullah and Ahmad Darwish",
        "en.sahih" to "English – by Saheeh International",
        "en.sarwar" to "English – by Muhammad Sarwar",
        "en.yusufali" to "English – by Abdullah Yusuf Ali",
        "en.maududi" to "English – by Abul Ala Maududi",
        "en.shakir" to "English – by Mohammad Habib Shakir",
        "en.transliteration" to "English – by English Transliteration",
        "fr.hamidullah" to "French – by Muhammad Hamidullah",
        "de.aburida" to "German – by Abu Rida Muhammad ibn Ahmad ibn Rassoul",
        "de.bubenheim" to "German – by A. S. F. Bubenheim and N. Elyas",
        "de.khoury" to "German – by Adel Theodor Khoury",
        "de.zaidan" to "German – by Amir Zaidan",
        "ha.gumi" to "Hausa – by Abubakar Mahmoud Gumi",
        "hi.hindi" to "Hindi – by Suhel Farooq Khan and Saifur Rahman Nadwi",
        "hi.farooq" to "Hindi – by Muhammad Farooq Khan and Muhammad Ahmed",
        "id.muntakhab" to "Indonesian – by Muhammad Quraish Shihab et al.",
        "it.piccardo" to "Italian – by Hamza Roberto Piccardo",
        "ku.asan" to "Kurdish – by Burhan Muhammad-Amin",
        "ms.basmeih" to "Malay – by Abdullah Muhammad Basmeih",
        "ml.abdulhameed" to "Malayalam – by Cheriyamundam Abdul Hameed and Kunhi Mohammed Parappoor",
        "no.berg" to "Norwegian – by Einar Berg",
        "fa.ayati" to "Persian – by AbdolMohammad Ayati",
        "fa.fooladvand" to "Persian – by Mohammad Mahdi Fooladvand",
        "fa.ghomshei" to "Persian – by Mahdi Elahi Ghomshei",
        "fa.makarem" to "Persian – by Naser Makarem Shirazi",
        "fa.ansarian" to "Persian – by Hussain Ansarian",
        "fa.bahrampour" to "Persian – by Abolfazl Bahrampour",
        "fa.khorramshahi" to "Persian – by Baha'oddin Khorramshahi",
        "fa.mojtabavi" to "Persian – by Sayyed Jalaloddin Mojtabavi",
        "fa.khorramdel" to "Persian – by Mostafa Khorramdel",
        "fa.moezzi" to "Persian – by Mohammad Kazem Moezzi",
        "pl.bielawskiego" to "Polish – by Józefa Bielawskiego",
        "pt.elhayek" to "Portuguese – by Samir El-Hayek",
        "ro.grigore" to "Romanian, Moldavian, Moldovan – by George Grigore",
        "ru.kuliev" to "Russian – by Elmir Kuliev",
        "ru.osmanov" to "Russian – by Magomed-Nuri Osmanovich Osmanov",
        "ru.porokhova" to "Russian – by V. Porokhova",
        "ru.abuadel" to "Russian – by Abu Adel",
        "ru.krachkovsky" to "Russian – by Ignaty Yulianovich Krachkovsky",
        "ru.muntahab" to "Russian – by Ministry of Awqaf, Egypt",
        "ru.sablukov" to "Russian – by Gordy Semyonovich Sablukov",
        "sd.amroti" to "Sindhi – by Taj Mehmood Amroti",
        "so.abduh" to "Somali – by Mahmud Muhammad Abduh",
        "es.cortes" to "Spanish; Castilian – by Julio Cortes",
        "es.asad" to "Spanish; Castilian – by Muhammad Asad - Abdurrasak Pérez",
        "sw.barwani" to "Swahili – by Ali Muhsin Al-Barwani",
        "sv.bernstrom" to "Swedish – by Knut Bernström",
        "tg.ayati" to "Tajik – by AbdolMohammad Ayati",
        "ta.tamil" to "Tamil – by Jan Turst Foundation",
        "tt.nugman" to "Tatar – by Yakub Ibn Nugman",
        "th.thai" to "Thai – by King Fahad Quran Complex",
        "tr.ates" to "Turkish – by Suleyman Ates",
        "tr.bulac" to "Turkish – by Alİ Bulaç",
        "tr.diyanet" to "Turkish – by Diyanet Isleri",
        "tr.golpinarli" to "Turkish – by Abdulbaki Golpinarli",
        "tr.ozturk" to "Turkish – by Yasar Nuri Ozturk",
        "tr.transliteration" to "Turkish – by Muhammet Abay",
        "tr.vakfi" to "Turkish – by Diyanet Vakfi",
        "tr.yazir" to "Turkish – by Elmalili Hamdi Yazir",
        "tr.yildirim" to "Turkish – by Suat Yildirim",
        "tr.yuksel" to "Turkish – by Edip Yüksel",
        "ug.saleh" to "Uighur, Uyghur – by Muhammad Saleh",
        "ur.ahmedali" to "Urdu – by Ahmed Ali",
        "ur.jalandhry" to "Urdu – by Fateh Muhammad Jalandhry",
        "ur.jawadi" to "Urdu – by Syed Zeeshan Haider Jawadi",
        "ur.kanzuliman" to "Urdu – by Ahmed Raza Khan",
        "ur.qadri" to "Urdu – by Tahir ul Qadri",
        "ur.junagarhi" to "Urdu – by Muhammad Junagarhi",
        "ur.maududi" to "Urdu – by Abul A'ala Maududi",
        "uz.sodik" to "Uzbek – by Muhammad Sodik Muhammad Yusuf"
    )
}