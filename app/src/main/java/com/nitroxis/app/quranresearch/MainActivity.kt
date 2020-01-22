package com.nitroxis.app.quranresearch

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.HandlerCompat
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nitroxis.app.quranresearch.Adapter.SearchResultListAdapter
import com.nitroxis.app.quranresearch.Fragment.FilterFragment
import com.nitroxis.app.quranresearch.Fragment.HistoryFragment
import com.nitroxis.app.quranresearch.Fragment.SearchFragment
import com.nitroxis.app.quranresearch.Fragment.SearchResultFragment
import com.nitroxis.app.quranresearch.Utils.ApiFactory
import com.nitroxis.app.quranresearch.Utils.Model
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.okButton
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity(), FilterFragment.OnFragmentInteractionListener,
    HistoryFragment.OnFragmentInteractionListener, SearchFragment.OnFragmentInteractionListener,
    SearchResultFragment.OnFragmentInteractionListener {

    lateinit var filterFragment: FilterFragment
    lateinit var searchFragment: SearchFragment
    lateinit var historyFragment: HistoryFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        historyFragment = HistoryFragment.newInstance()
        searchFragment = SearchFragment.newInstance()
        filterFragment = FilterFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, searchFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
        bottomBar.onItemSelected = {

            if (it == 0) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, searchFragment)
//                    .addToBackStack(searchFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            if (it == 1) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, historyFragment)
//                    .addToBackStack(historyFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

            if (it == 2) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, filterFragment)
//                    .addToBackStack(filterFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

        }

        bottomBar.onItemReselected = {
            // toast("Item $it re-selected")
        }
        bottomBar.setActiveItem(0)

    }


    override fun onFragmentInteraction(uri: Uri) {
        //  searchFragment.onResult()
    }

    override fun onFetchNewAyats(model: Model.AyaSearchBody) {

        val api = ApiFactory(this@MainActivity!!.applicationContext).myApi
        GlobalScope.launch {

            withContext(Dispatchers.Main) {
                val myDialog = indeterminateProgressDialog("Searching Query")
                myDialog.show()
                withContext(Dispatchers.IO) {
                    try {
                        //  val parameters = Model.AyaSearchBody()
                        val r = api.search(params = model)
                        if (r.isSuccessful && r.code() == 200) {
//                            myDialog.dismiss()
                            withContext(Dispatchers.Main) {
                                //                                toast(r.body().toString())
//                                toast(r.code().toString())
                                myDialog.dismiss()

                                val ayaresult = r.body()!!
                                val sf = SearchResultFragment.newInstance(ayaresult.ayas)

                                supportFragmentManager
                                    .beginTransaction()
                                    .replace(R.id.container, sf)
                                    .addToBackStack(searchFragment.toString())
                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                    .commit()

                                Log.d("response1", r.code().toString())
                                Log.d("response1", r.body().toString())
                                Log.d("response", r.message())
                                Log.d("error", r.message())
                                Log.d("response", r.message())

                            }
                            null
                        } else if (r.code() == 204) {
                            withContext(Dispatchers.Main) {
                                myDialog.dismiss()
                                alert("No Query Found For This Keyword") {
                                    okButton {
                                        it.dismiss()
//                                        finish()
                                    }
                                }.show()
                            }
                            null

                        } else {
                            myDialog.dismiss()
                            Log.d(
                                "The Result for Error", r.errorBody()?.string().toString()
                            )
                            Log.d("response code ", r.code().toString())


                            throw Exception(r.errorBody()?.string())

                        }
                    } catch (e: Exception) {
                        myDialog.dismiss()
                        Log.d("The Result for Error", e.message)
                        withContext(Dispatchers.Main) {

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
    }

}
