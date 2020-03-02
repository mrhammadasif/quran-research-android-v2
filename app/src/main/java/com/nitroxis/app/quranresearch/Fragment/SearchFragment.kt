package com.nitroxis.app.quranresearch.Fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.nitroxis.app.quranresearch.R
import com.nitroxis.app.quranresearch.Utils.DropDownValues
import com.nitroxis.app.quranresearch.Utils.Model
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import org.jetbrains.anko.okButton
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.browse
import org.jetbrains.anko.support.v4.toast
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchFragment : Fragment() {


    private var param1: String? = null
    private var param2: String? = null

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_search, container, false)
        v.privacy_policy.onClick { browse("https://nitroxis.com/privacy-policy") }
        v.bottom_logo.onClick { browse("https://play.google.com/store/apps/dev?id=5783184044573517448") }
        v.audio_search.onClick {

            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                Locale.getAvailableLocales()
            )
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                Locale.ENGLISH
            )
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                Locale.US
            )
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                Locale.GERMAN
            )
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                Locale.KOREA
            )
            if (intent.resolveActivity(context!!.packageManager) != null) {
                startActivityForResult(intent, 10)
            } else {
                toast("Your Device Don't Support Speech Input")
            }

        }

        /* val tagView: TagView = v.findViewById(R.id.text_view_show_more)
         tagView.addTagSeparator(TagSeparator.SPACE_SEPARATOR)
         tagView.addTagLimit(5)
         //tagView.setTagList(arrayListOf("cow"))
         tagView.setTagList()

         tagView.setTagTextColor(resources.getColor(R.color.black))

        val lang_adapter = ArrayAdapter(v.context, android.R.layout.simple_list_item_1, Language)
        v.lang_spinner.adapter = lang_adapter

            val keywords = arrayListOf<String>()
            var i=keywords.addAll(tagView.selectedTags.map { it.tagText })
             q = keywords.toTypedArray(),
          val word = v.keyword_search.toString()
          val Language = DropDownValues.lang.map {
            it.second
        }


        // Language Adapter
        v.lang_spinner.adapter = MySpinnerAdapter(v.context, DropDownValues.lang)
        v.lang_spinner.setSelection(8)

*/
        var language = DropDownValues.lang.map {
            it.second
        }

        val languageAdapter =
            ArrayAdapter(v.context, android.R.layout.simple_spinner_item, language)
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        v.lang_spinner.adapter = languageAdapter
        v.lang_spinner.setSelection(8)


        v.disclaimer.onClick {
            alert {
                val v = layoutInflater.inflate(R.layout.dialog_disclaimer, null)
                okButton {
                    it.dismiss()
                }
                customView = v
                isCancelable = false

            }.show()

        }
        var optionslanguage = DropDownValues.lang.map { it.first }
        v.lang_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // var langselect = optionslanguage[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
//        v.keyword_search.setText()


        // Search Button On CLick
        v.searchbtn.onClick {
            if (v.keyword_search.text.isNullOrEmpty()) {
                v.keyword_search.requestFocus()
                alert("Enter Keyword to Search") {
                    okButton { it.dismiss() }
                }.show()
                return@onClick
            }
            var selectedLanguage = ""

            val word = v.keyword_search.text.toString()
            selectedLanguage = DropDownValues.lang[v.lang_spinner.selectedItemPosition].first

            val parameter = Model.AyaSearchBody(
                q = word // ,lang = selectedLanguage
            )
            listener?.onFetchNewAyats(model = parameter)

        }

        return v
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            10 -> if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    result?.let {
                        toast(it[0])
                        view?.keyword_search?.append(it[0])
                    }
                }
            }
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        fun onFetchNewAyats(model: Model.AyaSearchBody)
    }

    fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (!isConnected) {

        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SearchFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

}
