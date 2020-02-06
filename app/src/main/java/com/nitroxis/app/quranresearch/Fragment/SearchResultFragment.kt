package com.nitroxis.app.quranresearch.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.nitroxis.app.quranresearch.Adapter.SearchResultListAdapter
import com.nitroxis.app.quranresearch.R
import com.nitroxis.app.quranresearch.Utils.DropDownValues
import com.nitroxis.app.quranresearch.Utils.DropDownValues.lang
import com.nitroxis.app.quranresearch.Utils.Model
import com.nitroxis.app.quranresearch.Utils.fromJson
import it.sephiroth.android.library.rangeseekbar.RangeSeekBar
import kotlinx.android.synthetic.main.content_filers.*
import kotlinx.android.synthetic.main.content_filers.lang_spinner
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.android.synthetic.main.fragment_search_result.view.*
import kotlinx.android.synthetic.main.fragment_search_result.view.recycle_search
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.longToast
import org.jetbrains.anko.support.v4.toast


private const val ARG_PARAM1 = "result"


class SearchResultFragment : Fragment() {
    private var ayasResult: ArrayList<Model.AyaObject>? = null
    private var model: Model.AyaSearchBody? = null
    var ayastart = 0
    var ayaend = 114
    private var listener: OnFragmentInteractionListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val jsonRes = it.getString(ARG_PARAM1) ?: "[]"
            ayasResult = Gson().fromJson<ArrayList<Model.AyaObject>>(jsonRes)
            model = Gson().fromJson<Model.AyaSearchBody>(it.getString("model") ?: "{}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view: View = inflater.inflate(R.layout.fragment_search_result, container, false)
        /*    var view: View = inflater.inflate(R.layout.fragment_search_result, container, false)
            if (view == null) {
                view = inflater.inflate(
                    R.layout.fragment_search_result, container, false
                )
            } else {
                (view.parent as ViewGroup).removeView(view)
            } */
        view.recycle_search.layoutManager = LinearLayoutManager(view.context)
        view.recycle_search.adapter = ayasResult?.let { SearchResultListAdapter(it, view.context) }


        val mBottomSheetDialog = BottomSheetDialog(activity!!)
        val sheetView: View = activity!!.layoutInflater.inflate(R.layout.content_filers, null)
        mBottomSheetDialog.setContentView(sheetView)
        view.filtermore.onClick {


            mBottomSheetDialog.show()
            val Origin = DropDownValues.origin.map {
                it.second
            }
            val origin_adapter =
                ArrayAdapter(view.context, R.layout.text, Origin)
            mBottomSheetDialog.origin_spinner.adapter = origin_adapter

            val Edition = DropDownValues.edition.map {
                it.second
            }
            val edition_adapter =
                ArrayAdapter(view.context, R.layout.text, Edition)

            mBottomSheetDialog.edition_spinner.adapter = edition_adapter

            val sajda = DropDownValues.sajda.map {
                it.second
            }
            val sajdaarray = arrayListOf("---Any One---", "Yes", "No")

            val sajda_adapter =
                ArrayAdapter(view.context, R.layout.text, sajdaarray)

            mBottomSheetDialog.sajda_spinner.adapter = sajda_adapter

            val Language = lang.map {
                it.second
            }


            val lang_adapter =
                ArrayAdapter(view.context, R.layout.text, Language)
            mBottomSheetDialog.lang_spinner.adapter = lang_adapter

            mBottomSheetDialog.rangebarayat.setOnRangeSeekBarChangeListener(object :
                RangeSeekBar.OnRangeSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: RangeSeekBar,
                    progressStart: Int,
                    progressEnd: Int,
                    fromUser: Boolean
                ) {
                    mBottomSheetDialog.range_seekbar.text =
                        seekBar.progressStart.toString() + " - " + seekBar.progressEnd.toString()
                    ayastart = seekBar.progressStart
                    ayaend = seekBar.progressEnd
                    /*    if (fromUser) {
                            v.ayattext1.setText(seekBar.progressStart.toString())
                            v.ayattext2.setText(seekBar.progressEnd.toString())
                        }*/
                }

                override fun onStartTrackingTouch(seekBar: RangeSeekBar) {}
                override fun onStopTrackingTouch(seekBar: RangeSeekBar) {}
            })
        val b=   mBottomSheetDialog.edit_keyowrd.text.indexOf(model!!.q)
            toast(b.toString())
            mBottomSheetDialog.resetfilter.onClick {
                mBottomSheetDialog.edit_keyowrd.setText(model!!.q)
            }
            mBottomSheetDialog.applyfilter.onClick {

                val keyword = model!!.q
                val languageselected = model?.lang

                val enteredkeyword = mBottomSheetDialog.edit_keyowrd.text.toString()

                val Edition =
                    DropDownValues.edition[mBottomSheetDialog.edition_spinner.selectedItemPosition].first
                val origin =
                    DropDownValues.origin[mBottomSheetDialog.origin_spinner.selectedItemPosition].first
                val lang =
                    DropDownValues.lang[mBottomSheetDialog.lang_spinner.selectedItemPosition].first

                val searchfilters = Model.AyaSearchBody(
                    q = enteredkeyword,
                    lang = lang
                )

                model = searchfilters
                model?.let { it1 -> listener?.onFetchNewAyats(it1) }
                mBottomSheetDialog.dismiss()

            }
        }

        return view
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
    }


    interface OnFragmentInteractionListener {
        fun onFetchNewAyats(model: Model.AyaSearchBody)

    }

    companion object {
        @JvmStatic
        fun newInstance(result: ArrayList<Model.AyaObject>, model: Model.AyaSearchBody) =
            SearchResultFragment().apply {
                arguments = Bundle().apply {
                    Log.d("countRecv", result.size.toString())
                    putString("result", Gson().toJson(result))
                    putString("model", Gson().toJson(model))
                }
            }
    }
}
