package com.nitroxis.app.quranresearch.Fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.nitroxis.app.quranresearch.R
import com.nitroxis.app.quranresearch.Utils.ApiFactory
import com.nitroxis.app.quranresearch.Utils.DropDownValues
import com.nitroxis.app.quranresearch.Utils.Model
import com.skyhope.materialtagview.TagView
import com.skyhope.materialtagview.enums.TagSeparator
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.okButton
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onItemSelectedListener
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.indeterminateProgressDialog
import org.jetbrains.anko.support.v4.toast


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    var runnable: Runnable? = null
    var runnable2: Runnable? = null
    lateinit var selected_language: String
    lateinit var selected_keyword: Array<String>
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
        val v = inflater.inflate(R.layout.fragment_search, container, false)

        val tagView: TagView = v.findViewById(R.id.text_view_show_more)
        tagView.addTagSeparator(TagSeparator.SPACE_SEPARATOR)

        val tagList = arrayOf("Cow", "Ants")
        tagView.setTagList(*tagList)
        tagView.addTagLimit(5)
        val keyword = tagView.toString()
        selected_keyword = arrayOf(keyword)
        tagView.setTagBackgroundColor(R.color.colorAccent)
        //val g= arrayOf(tagView)

        val Language = DropDownValues.lang.map {
            it.second
        }

        val lang_adapter = ArrayAdapter(v.context, android.R.layout.simple_spinner_item, Language)
        lang_adapter.setDropDownViewResource(android.R.layout.simple_list_item_1)
        v.lang_spinner.adapter = lang_adapter

        /*   v.lang_spinner.onItemSelectedListener {
               onItemSelected { adapterView, view, pos, l ->
                   toast(pos.toString())
               }
           }
    */
        /*  runnable = Runnable {
              GlobalScope.launch {


                  v.lang_spinner.onItemSelectedListener {
                      this.onItemSelected { adapterView, view, position, l ->
                          if (position == 0) {
                              val u = v.lang_spinner.setSelection(8)
                              Log.d("h1235", u.toString())
                              toast(u.toString())
                          } else {
                              GlobalScope.launch {
                                  selected_language = v.lang_spinner[position].toString()
                                  val int = v.lang_spinner[position].toString()
                                  Log.d("h1235", int.toString())

                                  toast(int)

                              }

                          }
                      }
                  }
              }
          } */


        GlobalScope.launch {
            searchbtn.onClick {
                val api = ApiFactory(context!!.applicationContext).myApi
                toast(api.toString())


                val dialog = indeterminateProgressDialog("Searching Query")
                dialog.show()
                withContext(Dispatchers.IO) {
                    try {
                        val parameters = Model.AyaSearchBody(
                            q = selected_keyword,
                            lang = selected_language
                        )
                        val r = api.search(params = parameters)
                        if (r.isSuccessful && r.code() == 200) {
                            withContext(Dispatchers.Main) {
                                //  dialog.dismiss()

                                Log.d("response", r.message())
                                Log.d("error", r.message())
                                Log.d("response", r.message())
                            }
                        } else {
                            Log.d("The Result for Error", r.errorBody()?.string().toString())
                            Log.d("response code ", r.code().toString())
                            throw Exception(r.errorBody()?.string())
                            dialog.dismiss()
                        }
                    } catch (e: Exception) {
                        Log.d("The Result for Error", e.message)
                        withContext(Dispatchers.Main) {
                            dialog.dismiss()

                            alert(e.message.toString()) {
                                okButton {
                                    it.dismiss()
                                }

                            }.show()
                        }
                    }
                }

            }
        }

        v.lang_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                if (p0 == null) {
                    v.lang_spinner.setSelection(8)
                    toast("$p0")
                }
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                selected_language = p0?.selectedItem.toString()
                toast(selected_language)
                //  Log.d("TAG", p0?.selectedItem.toString())
            }
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
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SearchFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }


}
