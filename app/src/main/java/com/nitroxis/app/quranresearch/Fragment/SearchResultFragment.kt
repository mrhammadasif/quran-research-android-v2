package com.nitroxis.app.quranresearch.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.nitroxis.app.quranresearch.Adapter.SearchResultListAdapter
import com.nitroxis.app.quranresearch.R
import com.nitroxis.app.quranresearch.Utils.DropDownValues
import com.nitroxis.app.quranresearch.Utils.Model
import com.nitroxis.app.quranresearch.Utils.fromJson
import it.sephiroth.android.library.rangeseekbar.RangeSeekBar
import kotlinx.android.synthetic.main.content_filers.view.*
import kotlinx.android.synthetic.main.content_filers.view.edition_spinner
import kotlinx.android.synthetic.main.content_filers.view.origin_spinner
import kotlinx.android.synthetic.main.content_filers.view.sajda_spinner
import kotlinx.android.synthetic.main.fragment_filter.view.*
import kotlinx.android.synthetic.main.fragment_filter.view.filterbtn
import kotlinx.android.synthetic.main.fragment_filter.view.rangeSeekBarayat
import kotlinx.android.synthetic.main.fragment_filter.view.rangeSeekBarsurah
import kotlinx.android.synthetic.main.fragment_filter.view.range_seekbar
import kotlinx.android.synthetic.main.fragment_filter.view.range_seekbar1
import kotlinx.android.synthetic.main.fragment_search_result.view.*
import org.jetbrains.anko.okButton
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.toast

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
        myView.filtermore.onClick {
            alert {
                val alertdialog =
                    LayoutInflater.from(context).inflate(R.layout.content_filers, null, false)
                val Origin = DropDownValues.origin.map {
                    it.second
                }

                val origin_adapter =
                    ArrayAdapter(myView.context, android.R.layout.simple_spinner_item, Origin)
                origin_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                alertdialog.origin_spinner.adapter = origin_adapter

                val Edition = DropDownValues.editionType.map {
                    it.second
                }

                val edition_adapter =
                    ArrayAdapter(myView.context, android.R.layout.simple_spinner_item, Edition)
                edition_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                alertdialog.edition_spinner.adapter = edition_adapter


                val sajda = DropDownValues.sajda.map {
                    it.second
                }
                val sajdaarray = arrayListOf<String>("---Any One---", "Yes", "No")

                val sajda_adapter =
                    ArrayAdapter(myView.context, android.R.layout.simple_spinner_item, sajdaarray)
                sajda_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                alertdialog.sajda_spinner.adapter = sajda_adapter

                alertdialog.rangeSeekBarsurah.setOnRangeSeekBarChangeListener(object :
                    RangeSeekBar.OnRangeSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: RangeSeekBar,
                        progressStart: Int,
                        progressEnd: Int,
                        fromUser: Boolean
                    ) {
                        alertdialog.range_seekbar1.text =
                            seekBar.progressStart.toString() + " - " + seekBar.progressEnd.toString()


/*
                if (fromUser) {
                    v.surahtext1.setText(seekBar.progressStart.toString())
                    v.surahtext2.setText(seekBar.progressEnd.toString())
                } */

                    }

                    override fun onStartTrackingTouch(seekBar: RangeSeekBar) {

                    }

                    override fun onStopTrackingTouch(seekBar: RangeSeekBar) {

                    }
                })
                alertdialog.rangeSeekBarayat.setOnRangeSeekBarChangeListener(object :
                    RangeSeekBar.OnRangeSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: RangeSeekBar,
                        progressStart: Int,
                        progressEnd: Int,
                        fromUser: Boolean
                    ) {

                        alertdialog.range_seekbar.text =
                            seekBar.progressStart.toString() + " - " + seekBar.progressEnd.toString()


                        /*    if (fromUser) {
                                v.ayattext1.setText(seekBar.progressStart.toString())
                                v.ayattext2.setText(seekBar.progressEnd.toString())
                            }*/
                    }

                    override fun onStartTrackingTouch(seekBar: RangeSeekBar) {
                    }

                    override fun onStopTrackingTouch(seekBar: RangeSeekBar) {

                    }
                })

                alertdialog.c_filterbtn.onClick {
                    listener?.searchfilters(ayaSearchBody = )
                }
                okButton { it.dismiss() }
                customView = alertdialog
                isCancelable = false

            }.show()

        }

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
        fun searchfilters(ayaSearchBody: Model.AyaSearchBody)
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
