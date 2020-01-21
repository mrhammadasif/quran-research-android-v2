package com.nitroxis.app.quranresearch.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nitroxis.app.quranresearch.R
import com.nitroxis.app.quranresearch.Utils.Model
import kotlinx.android.synthetic.main.recycle_result_item.view.*


internal class SearchResultListAdapter(
    private val arrayList: ArrayList<Model.AyaObject>,
    private val context: Context
) : RecyclerView.Adapter<SearchResultListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val v = LayoutInflater.from(context)
            .inflate(android.R.layout.simple_expandable_list_item_2, null, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])
    }

    override fun getItemCount(): Int {
        Log.d("countofayas", arrayList.size.toString())
        return arrayList.size
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(result: Model.AyaObject) {
            // itemView.findViewById<TextView>(android.R.layout.simple_expandable_list_item_1)
            itemView.findViewById<TextView>(android.R.id.text1).text = result.sourceText
            itemView.findViewById<TextView>(android.R.id.text2).text = result.text
            //  itemView.limit.text = result.sourceText
            //itemView.page.text = result.text
        }
    }
}
