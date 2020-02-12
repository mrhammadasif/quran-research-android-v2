package com.nitroxis.app.quranresearch.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.nitroxis.app.quranresearch.Adapter.MySpinnerAdapter
import com.nitroxis.app.quranresearch.R
import com.nitroxis.app.quranresearch.Utils.DropDownValues
import com.nitroxis.app.quranresearch.Utils.Model
import kotlinx.android.synthetic.main.content_filers.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.android.synthetic.main.fragment_search.view.lang_spinner
import org.jetbrains.anko.okButton
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.toast


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    lateinit var selected_language: String
    // val selectedLanguage
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

        val languageAdapter = ArrayAdapter(v.context, android.R.layout.simple_spinner_item, language)
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        v.lang_spinner.adapter = languageAdapter
        v.lang_spinner.setSelection(8)

        var optionslanguage = DropDownValues.lang.map { it.first }
        v.lang_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    var langselect = optionslanguage[position]
                  }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
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
                q = word,
                lang = selectedLanguage
            )
            listener?.onFetchNewAyats(model = parameter)

        }

        return v
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
