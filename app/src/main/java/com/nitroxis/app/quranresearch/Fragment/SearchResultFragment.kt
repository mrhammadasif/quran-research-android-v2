package com.nitroxis.app.quranresearch.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nitroxis.app.quranresearch.Adapter.SearchResultListAdapter
import com.nitroxis.app.quranresearch.R
import com.nitroxis.app.quranresearch.Utils.Model
import com.nitroxis.app.quranresearch.Utils.fromJson
import kotlinx.android.synthetic.main.fragment_search_result.view.*

private const val ARG_PARAM1 = "result"



class SearchResultFragment : Fragment() {
    private var ayasResult: ArrayList<Model.AyaObject>? = null

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val jsonRes = it.getString(ARG_PARAM1) ?: "[]"
            ayasResult = Gson().fromJson<ArrayList<Model.AyaObject>>(jsonRes)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val myView = inflater.inflate(R.layout.fragment_search_result, container, false)

        myView.recycle_search.layoutManager = LinearLayoutManager(myView.context)
        myView.recycle_search.adapter =
            ayasResult?.let { SearchResultListAdapter(it, myView.context) }

        return myView
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
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(result: ArrayList<Model.AyaObject>) =
            SearchResultFragment().apply {
                arguments = Bundle().apply {
                    Log.d("countRecv", result.size.toString())
                    putString("result", Gson().toJson(result))
                }
            }
    }
}
