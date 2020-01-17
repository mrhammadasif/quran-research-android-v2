package com.nitroxis.app.quranresearch.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.nitroxis.app.quranresearch.R
import com.nitroxis.app.quranresearch.Utils.DropDownValues
import it.sephiroth.android.library.rangeseekbar.RangeSeekBar
import kotlinx.android.synthetic.main.fragment_filter.view.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FilterFragment : Fragment() {

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
    ): View?     {
        val v = inflater.inflate(R.layout.fragment_filter, container, false)

        val Origin = DropDownValues.origin.map {
            it.second
        }

        val origin_adapter = ArrayAdapter(v.context, android.R.layout.simple_spinner_item, Origin)
        origin_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        v.origin_spinner.adapter = origin_adapter

        val EDition = DropDownValues.editionType.map {
            it.second
        }

        val edition_adapter = ArrayAdapter(v.context, android.R.layout.simple_spinner_item, EDition)
        edition_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        v.edition_spinner.adapter = edition_adapter



        v.rangeSeekBarayat.setOnRangeSeekBarChangeListener(object :
            RangeSeekBar.OnRangeSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: RangeSeekBar,
                progressStart: Int,
                progressEnd: Int,
                fromUser: Boolean
            ) {
            }

            override fun onStartTrackingTouch(seekBar: RangeSeekBar) {}
            override fun onStopTrackingTouch(seekBar: RangeSeekBar) {}
        })
        v.rangeSeekBarsurah.setOnRangeSeekBarChangeListener(object :
            RangeSeekBar.OnRangeSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: RangeSeekBar,
                progressStart: Int,
                progressEnd: Int,
                fromUser: Boolean
            ) {
            }

            override fun onStartTrackingTouch(seekBar: RangeSeekBar) {}
            override fun onStopTrackingTouch(seekBar: RangeSeekBar) {}
        })

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
            FilterFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
