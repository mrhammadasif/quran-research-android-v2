package com.nitroxis.app.quranresearch.Adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.nitroxis.app.quranresearch.R
import kotlinx.android.synthetic.main.spinner_item.view.*
import org.jetbrains.anko.layoutInflater

class MySpinnerAdapter(val context: Context, val values: ArrayList<Pair<String, String>>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = context.layoutInflater.inflate(R.layout.spinner_item, null)
        view.text1.text = getItem(position).first
        view.text2.text = getItem(position).second
        return view
    }

    override fun getItem(position: Int): Pair<String, String> = values[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = values.size
}