package com.nitroxis.app.quranresearch.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.nitroxis.app.quranresearch.R
import com.nitroxis.app.quranresearch.Utils.DropDownValues
import it.sephiroth.android.library.rangeseekbar.RangeSeekBar
import kotlinx.android.synthetic.main.fragment_filter.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.toast


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FilterFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    lateinit var ayastart: String
    lateinit var ayaend: String
    lateinit var surahstart: String
    lateinit var surahend: String


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
        val v = inflater.inflate(R.layout.fragment_filter, container, false)

        val Origin = DropDownValues.origin.map {
            it.second
        }

        val origin_adapter = ArrayAdapter(v.context, android.R.layout.simple_spinner_item, Origin)
        origin_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        v.origin_spinner.adapter = origin_adapter

        val Edition = DropDownValues.editionType.map {
            it.second
        }

        val edition_adapter = ArrayAdapter(v.context, android.R.layout.simple_spinner_item, Edition)
        edition_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        v.edition_spinner.adapter = edition_adapter


        val sajda = DropDownValues.sajda.map {
            it.second
        }

        val sajda_adapter = ArrayAdapter(v.context, android.R.layout.simple_spinner_item, sajda)
        sajda_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        v.sajda_spinner.adapter = sajda_adapter

        v.rangeSeekBarsurah.setOnRangeSeekBarChangeListener(object :
            RangeSeekBar.OnRangeSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: RangeSeekBar,
                progressStart: Int,
                progressEnd: Int,
                fromUser: Boolean
            ) {
                v.range_seekbar1.text =
                    seekBar.progressStart.toString() + " - " + seekBar.progressEnd.toString()
                surahstart = seekBar.progressStart.toString()
                surahend = seekBar.progressEnd.toString()
                toast(v.range_seekbar1.toString())

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
        v.rangeSeekBarayat.setOnRangeSeekBarChangeListener(object :
            RangeSeekBar.OnRangeSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: RangeSeekBar,
                progressStart: Int,
                progressEnd: Int,
                fromUser: Boolean
            ) {

                v.range_seekbar.text =
                    seekBar.progressStart.toString() + " - " + seekBar.progressEnd.toString()
                ayastart = seekBar.progressStart.toString()
                ayaend = seekBar.progressEnd.toString()

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
/*
        v.ayattext1.doOnTextChanged { text, start, count, after ->
            v.rangeSeekBarayat.setProgress(
                text.toString().toIntOrNull() ?: 1,
                v.ayattext2.toString().toIntOrNull() ?: 114,
                true
            )
        }

        v.ayattext2.doOnTextChanged { text, start, count, after ->
            v.rangeSeekBarayat.setProgress(
                v.ayattext1.toString().toIntOrNull() ?: 1,
                text.toString().toIntOrNull() ?: 114,
                true
            )
        }


        v.surahtext2.doOnTextChanged { text, start, count, after ->
            v.rangeSeekBarsurah.setProgress(
                v.surahtext1.toString().toIntOrNull() ?: 1,
                text.toString().toIntOrNull() ?: 114,
                true
            )
        }*/

        v.filterbtn.onClick {
            var i = v.range_seekbar1.toString()
            val edition = DropDownValues.editionType[v.edition_spinner.selectedItemPosition].first
            val origin = DropDownValues.origin[v.origin_spinner.selectedItemPosition].first
            val sajda = DropDownValues.sajda[v.sajda_spinner.selectedItemPosition].first
            val v = v.range_seekbar.text.toString()


        }
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
        // fun newfilters(filtermodel: Model.AyaSearchBody)

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FilterFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
