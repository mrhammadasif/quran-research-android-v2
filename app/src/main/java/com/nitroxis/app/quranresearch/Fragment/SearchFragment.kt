package com.nitroxis.app.quranresearch.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.nitroxis.app.quranresearch.R
import com.nitroxis.app.quranresearch.Utils.ApiFactory
import com.nitroxis.app.quranresearch.Utils.DropDownValues
import com.skyhope.materialtagview.TagView
import com.skyhope.materialtagview.enums.TagSeparator
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.okButton
import org.jetbrains.anko.sdk27.coroutines.onItemSelectedListener
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.indeterminateProgressDialog


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchFragment : Fragment(),AdapterView.OnItemSelectedListener {

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

        val tagView: TagView = v.findViewById(R.id.text_view_show_more)
        tagView.addTagSeparator(TagSeparator.SPACE_SEPARATOR)

        val tagList = arrayOf("Cow", "Ants")
        tagView.setTagList(*tagList)
        tagView.addTagLimit(5)
        tagView.setTagBackgroundColor(R.color.colorAccent)
         Log.d("Entered Tags",tagView.tag.toString())
        val Language = DropDownValues.lang.map {
            it.second
        }
        val lang_adapter = ArrayAdapter(v.context, android.R.layout.simple_spinner_item, Language)
        lang_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        v.lang_spinner.adapter = lang_adapter
        v.lang_spinner.onItemSelectedListener {
            this.onItemSelected { adapterView, view, position, l ->
                v.lang_spinner.setSelection(8)
            }
        }

        val api = ApiFactory(v.context).myApi


     /*   GlobalScope.launch {
            val dialog = indeterminateProgressDialog("Searching Query")
            dialog.show()
            withContext(Dispatchers.IO) {
                try {
                    val r = api.search()
                    if (r.isSuccessful && r.code() == 200) {
                        withContext(Dispatchers.Main) {
                            dialog.dismiss()

                            Log.d("response", r.message())
                            Log.d("error", r.message())
                            Log.d("response", r.message())
                            //   startActivity<FilterFragment>()
                        }
                    } else {
                        Log.d("The Result for Error", r.errorBody()?.string().toString())
                        Log.d("response code ", r.code().toString())
                        throw Exception(r.errorBody()?.string())
                    }
                } catch (e: Exception) {
                    Log.d("The Result for Error", e.message)
                    withContext(Dispatchers.Main) {
                        dialog.dismiss()

                        alert("No Result Foud For this Keyword.Please Enter a Valid Keyword") {
                            okButton {
                                it.dismiss()
                            }
                        }.show()
                    }
                }
            }
        }*/
        return v

    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
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
