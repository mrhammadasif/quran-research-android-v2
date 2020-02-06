package com.nitroxis.app.quranresearch.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nitroxis.app.quranresearch.R
import com.nitroxis.app.quranresearch.Utils.DropDownValues
import com.nitroxis.app.quranresearch.Utils.Model
import com.skyhope.materialtagview.TagView
import com.skyhope.materialtagview.enums.TagSeparator
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.toast


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    lateinit var selected_language: String
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
         */
        val word = v.keyword_search.toString()

        val Language = DropDownValues.lang.map {
            it.second
        }

        val lang_adapter = ArrayAdapter(v.context, R.layout.text, Language)
        v.lang_spinner.adapter = lang_adapter

        v.searchbtn.onClick {
            var selectedLanguage = ""
/*
            val keywords = arrayListOf<String>()
            var i=keywords.addAll(tagView.selectedTags.map { it.tagText })
             q = keywords.toTypedArray(),
 */
            val word = v.keyword_search.text.toString()
            selectedLanguage = DropDownValues.lang[v.lang_spinner.selectedItemPosition].first
            val parameter = Model.AyaSearchBody(
                q = word,
                lang = selectedLanguage
            )

            listener?.onFetchNewAyats(model = parameter)

        }

        v.lang_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                if (p0 == null) {
                    v.lang_spinner.setSelection(8)
                    //toast("$p0")
                }
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                selected_language = p0?.selectedItem.toString()

            }
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
        //   fun onFetchNewAyats(model: Model.AyaSearchResult)
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
