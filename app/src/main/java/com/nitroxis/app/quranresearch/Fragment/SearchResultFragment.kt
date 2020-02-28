package com.nitroxis.app.quranresearch.Fragment

import android.annotation.SuppressLint
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
import com.innovattic.rangeseekbar.RangeSeekBar
import com.nitroxis.app.quranresearch.Adapter.SearchResultListAdapter
import com.nitroxis.app.quranresearch.R
import com.nitroxis.app.quranresearch.Utils.DropDownValues
import com.nitroxis.app.quranresearch.Utils.Model
import com.nitroxis.app.quranresearch.Utils.fromJson
import com.nitroxis.app.quranresearch.Utils.isNetworkReachable
import kotlinx.android.synthetic.main.content_filers.*
import kotlinx.android.synthetic.main.fragment_search_result.view.*
import org.jetbrains.anko.okButton
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.alert


private const val ARG_PARAM1 = "result"


class SearchResultFragment : Fragment() {
    private var ayasResult: ArrayList<Model.AyaObject>? = null
    private var model: Model.AyaSearchBody? = null
    private var listener: OnFragmentInteractionListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val jsonRes = it.getString(ARG_PARAM1) ?: "[]"
            ayasResult = Gson().fromJson<ArrayList<Model.AyaObject>>(jsonRes)
            model = Gson().fromJson<Model.AyaSearchBody>(it.getString("model") ?: "{}")
        }
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_search_result, container, false)

        view.recycle_search.layoutManager = LinearLayoutManager(view.context)
        view.recycle_search.adapter = ayasResult?.let { SearchResultListAdapter(it, view.context) }

        //BottomSheet Dialog
        val mBottomSheetDialog = BottomSheetDialog(activity!!)
        val sheetView: View = activity!!.layoutInflater.inflate(R.layout.content_filers, null)
        mBottomSheetDialog.setContentView(sheetView)
        mBottomSheetDialog.setCancelable(false)
        mBottomSheetDialog.setCanceledOnTouchOutside(false)


        //Filter Button
        view.filterbtn.onClick {

            mBottomSheetDialog.show()

            //searchable Edition adapter
            val edition = DropDownValues.surah.map {
                it.second
            }

            val adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, edition)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            mBottomSheetDialog.sspinner.adapter = adapter
            mBottomSheetDialog.sspinner.setSelection(0)

            val options = DropDownValues.surah.map { it.first }

            mBottomSheetDialog.sspinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                      var  surahselect = options[position]
                    }
                    override fun onNothingSelected(parent: AdapterView<*>) {}
                }

            //Seachable Language Adapter
            val language = DropDownValues.lang.map {
                it.second
            }

            val languageAdapter =
                ArrayAdapter(view.context, android.R.layout.simple_spinner_item, language)
            languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            mBottomSheetDialog.langspinner.adapter = languageAdapter
            mBottomSheetDialog.langspinner.setSelection(8)

            val optionslanguage = DropDownValues.lang.map { it.first }

            mBottomSheetDialog.langspinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
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
            //Searchable Edition Type Adapter
            var Editiontype = DropDownValues.editionType.map {
                it.second
            }
            val typeAdapter =
                ArrayAdapter(view.context, android.R.layout.simple_spinner_item, Editiontype)
            typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            mBottomSheetDialog.ed_typespinner.adapter = typeAdapter
            mBottomSheetDialog.ed_typespinner.setSelection(0)

            var optionstype = DropDownValues.editionType.map { it.first }

            mBottomSheetDialog.ed_typespinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                     var   editiontypeselect = optionstype[position]
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {

                    }
                }

            //Searchable Edition  Adapter
            var Edition = DropDownValues.edition.map {
                it.second
            }
            val editionAdapter =
                ArrayAdapter(view.context, android.R.layout.simple_spinner_item, Edition)
            editionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            mBottomSheetDialog.edition_spinner.adapter = editionAdapter
            mBottomSheetDialog.edition_spinner.setSelection(0)

            var optionsedition = DropDownValues.edition.map { it.first }

            mBottomSheetDialog.edition_spinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                      var   editionselect = optionsedition[position]
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {

                    }
                }


            //Searchable origin  Adapter
            var origin = DropDownValues.origin.map {
                it.second
            }
            val originAdapter =
                ArrayAdapter(view.context, android.R.layout.simple_spinner_item, origin)
            originAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            mBottomSheetDialog.origin_spinner.adapter = originAdapter
            mBottomSheetDialog.origin_spinner.setSelection(0)

            var optionsorigin = DropDownValues.origin.map { it.first }

            mBottomSheetDialog.origin_spinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                       var originselect = optionsorigin[position]
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {

                    }
                }
            //Searchable Sajda Adapter
            var sajda = DropDownValues.sajda.map {
                it.second
            }
            val sajdaAdapter =
                ArrayAdapter(view.context, android.R.layout.simple_spinner_item, sajda)
            originAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            mBottomSheetDialog.sajda_spinner.adapter = sajdaAdapter
            mBottomSheetDialog.sajda_spinner.setSelection(0)

            var optionssajda = DropDownValues.sajda.map { it.first }

            mBottomSheetDialog.sajda_spinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                       var sajdaselect = optionssajda[position]
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {}
                }



            mBottomSheetDialog.edit_keyowrd.setText(model!!.q)

            //Range Seekbar
            mBottomSheetDialog.rangeSeekBar.setMinThumbValue(0)
            mBottomSheetDialog.rangeSeekBar.setMaxThumbValue(286)

            mBottomSheetDialog.rangeSeekBar.seekBarChangeListener =
                object : RangeSeekBar.SeekBarChangeListener {

                    override fun onStartedSeeking() {}

                    override fun onStoppedSeeking() {}

                    override fun onValueChanged(minThumbValue: Int, maxThumbValue: Int) {

                        mBottomSheetDialog.range_text.text = "${minThumbValue}-${maxThumbValue}"
                    }
                }

            //Filter Dialog Reset Button
            mBottomSheetDialog.resetfilter.onClick {
                //resetting all filters in bottom sheet
                mBottomSheetDialog.edit_keyowrd.setText("")
                mBottomSheetDialog.edition_spinner.setSelection(0)
                mBottomSheetDialog.langspinner.setSelection(8)
                mBottomSheetDialog.ed_typespinner.setSelection(0)
                mBottomSheetDialog.sajda_spinner.setSelection(0)
                mBottomSheetDialog.origin_spinner.setSelection(0)
                mBottomSheetDialog.sspinner.setSelection(0)
                mBottomSheetDialog.range_text.text = ""
                mBottomSheetDialog.rangeSeekBar.setMinThumbValue(0)
                mBottomSheetDialog.rangeSeekBar.setMaxThumbValue(286)

            }
            //Filter Dialog Close Button
            mBottomSheetDialog.closebtn.onClick {
                mBottomSheetDialog.range_text.text = ""
                mBottomSheetDialog.dismiss()
            }


            // APPLY FILTER after dialog is closed
            mBottomSheetDialog.applyfilter.onClick {


                if (mBottomSheetDialog.edit_keyowrd.text.isNullOrEmpty()) {
                    mBottomSheetDialog.edit_keyowrd.requestFocus()
                    mBottomSheetDialog.edit_keyowrd.error = "Enter Keyword To Search"
                    return@onClick
                }
                var ayaEnd: Int? = null
                var ayaStart: Int? = null
                if (mBottomSheetDialog.rangeSeekBar.getMinThumbValue() > 0 || mBottomSheetDialog.rangeSeekBar.getMaxThumbValue() < 286) {
                    ayaStart = mBottomSheetDialog.rangeSeekBar.getMinThumbValue()
                    ayaEnd = mBottomSheetDialog.rangeSeekBar.getMaxThumbValue()
                }


                val enteredKeyword = mBottomSheetDialog.edit_keyowrd.text.toString()
                val lang = DropDownValues.lang[mBottomSheetDialog.langspinner.selectedItemPosition].first

                val edition = DropDownValues.edition[mBottomSheetDialog.edition_spinner.selectedItemPosition].first

                val origin = DropDownValues.origin[mBottomSheetDialog.origin_spinner.selectedItemPosition].first

                val edition_type = DropDownValues.editionType[mBottomSheetDialog.ed_typespinner.selectedItemPosition].first
                val surah = DropDownValues.surah[mBottomSheetDialog.sspinner.selectedItemPosition].first
                val sajda = DropDownValues.sajda[mBottomSheetDialog.sajda_spinner.selectedItemPosition].first

                     val posOrigin = mBottomSheetDialog.origin_spinner.selectedItemPosition
                     val posEdition = mBottomSheetDialog.edition_spinner.selectedItemPosition
                     val posEditionType = mBottomSheetDialog.ed_typespinner.selectedItemPosition
                     val posSurah = mBottomSheetDialog.sspinner.selectedItemPosition
                     val posSajda = mBottomSheetDialog.sajda_spinner.selectedItemPosition

                val searchFilters = Model.AyaSearchBody(
                    q = enteredKeyword,
                    lang = lang,
                    edition = edition,
                    origin = origin,
                    type = edition_type,
                    sura = surah,
                    ayaTo = ayaEnd,
                    ayaFrom = ayaStart,
                    sajda = sajda
                )

                if (posSajda == 0) {
                    searchFilters.sura = null
                }
                if (posSurah == 0) {
                    searchFilters.sura = null
                }
                if (posOrigin == 0) {
                    searchFilters.origin = null
                }

                if (posEdition == 0) {
                    searchFilters.edition = null
                }

                if (posEditionType == 0) {
                    searchFilters.type = null
                }

                val filteredModel = searchFilters

                if (context?.isNetworkReachable() == true) {
                    filteredModel?.let { it1 -> listener?.onFetchNewAyats(it1) }
                    alert(filteredModel.toString()) {
                        okButton { it.dismiss() }
                    }.show()
                } else {
                    alert("No Internet Connection.Please Check Your Internet Connection And Try Again!") {
                        okButton {
                            it.dismiss()
                        }
                    }.show()
                }
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
        listener = null
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
