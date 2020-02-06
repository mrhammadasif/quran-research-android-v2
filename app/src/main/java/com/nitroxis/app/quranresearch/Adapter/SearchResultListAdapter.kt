package com.nitroxis.app.quranresearch.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val v = LayoutInflater.from(context).inflate(R.layout.recycle_result_item, null, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])
    }

    override fun getItemCount(): Int {
        Log.d("countofa" + "yas", arrayList.size.toString())
        return arrayList.size
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(result: Model.AyaObject) {
            // itemView.findViewById<TextView>(android.R.layout.simple_expandable_list_item_1)
            //  itemView.findViewById<TextView>(android.R.id.text1).text = result.sourceText
            //itemView.findViewById<TextView>(android.R.id.text2).text = result.text
            itemView.sura.text = "${result.sura?.name}" + "-" + "${result.sura?.order?.quran}"
            itemView.aya.text = result.aya.toString()
            itemView.edition.text = result.edition?.name
            itemView.sourcetext.text = result.sourceText
            itemView.text.text = result.text

        }
    }
}
