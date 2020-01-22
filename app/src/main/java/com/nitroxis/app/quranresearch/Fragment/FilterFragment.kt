package com.nitroxis.app.quranresearch.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.nitroxis.app.quranresearch.R
import com.nitroxis.app.quranresearch.Utils.DropDownValues
import it.sephiroth.android.library.rangeseekbar.RangeSeekBar
import kotlinx.android.synthetic.main.fragment_filter.view.*
import org.jetbrains.anko.sdk27.coroutines.textChangedListener
import org.jetbrains.anko.support.v4.toast


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

        //val sajda = if (v.sajda_checkbox.isChecked) true else false

        v.rangeSeekBarayat.setOnRangeSeekBarChangeListener(object :
            RangeSeekBar.OnRangeSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: RangeSeekBar,
                progressStart: Int,
                progressEnd: Int,
                fromUser: Boolean
            ) {

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

                val ayattextend = v.ayattext2.text
                toast(progressEnd.toString())
                v.range_seekbar.text = seekBar.progressStart.toString() + " - " + seekBar.progressEnd
                //  progressEnd.absoluteValue(ayattextend.toString())

                if(fromUser) {
                    v.ayattext1.setText(seekBar.progressStart.toString())
                    v.ayattext2.setText(seekBar.progressEnd.toString())
                }
            }

            override fun onStartTrackingTouch(seekBar: RangeSeekBar) {
                //toast(seekBar.progressEnd.toString())
            }

            override fun onStopTrackingTouch(seekBar: RangeSeekBar) {

            }
        })

//        v.ayattext2.textChangedListener {
//            onTextChanged { charSequence, i, i2, i3 ->
//
//            }
//        }
//        v.ayattext1.doOnTextChanged { text, start, count, after ->
//            v.rangeSeekBarayat.setProgress(text.toString().toInt(), v.ayattext2.toString().toInt(), true)
//        }
        v.ayattext2.doOnTextChanged { text, start, count, after ->
            v.rangeSeekBarayat.setProgress(v.ayattext1.toString().toIntOrNull() ?: 1, text.toString().toIntOrNull() ?: 114, true)
        }

        //initializeSeekBar(v.rangeSeekBarayat, v.range_seekbar)
        //initializeSeekBar(v.rangeSeekBarsurah, v.range_seekbar2)

        return v

    }

    /*  private fun initializeSeekBar(seekbar: RangeSeekBar, textView: TextView) {
          seekbar.setOnRangeSeekBarChangeListener(object : OnRangeSeekBarChangeListener {
              override fun onProgressChanged(
                  seekBar: RangeSeekBar,
                  progressStart: Int,
                  progressEnd: Int,
                  fromUser: Boolean
              ) {
                  updateRangeText(textView, seekBar)
              }

              override fun onStartTrackingTouch(seekBar: RangeSeekBar) {

              }

              override fun onStopTrackingTouch(seekBar: RangeSeekBar) {}
          })
          updateRangeText(textView, seekbar)
      }

      private fun updateRangeText(textView: TextView, seekBar: RangeSeekBar) {
          textView.text = seekBar.progressStart.toString() + " - " + seekBar.progressEnd
      } */


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
