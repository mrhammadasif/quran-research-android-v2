package com.nitroxis.app.quranresearch

import android.content.Context
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.nitroxis.app.quranresearch.Fragment.FilterFragment
import com.nitroxis.app.quranresearch.Fragment.HistoryFragment
import com.nitroxis.app.quranresearch.Fragment.SearchFragment
import com.nitroxis.app.quranresearch.Fragment.SearchResultFragment
import com.nitroxis.app.quranresearch.Utils.ApiFactory
import com.nitroxis.app.quranresearch.Utils.DropDownValues.lang
import com.nitroxis.app.quranresearch.Utils.Model
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.*


class MainActivity : AppCompatActivity(), FilterFragment.OnFragmentInteractionListener,
     SearchFragment.OnFragmentInteractionListener,
    SearchResultFragment.OnFragmentInteractionListener {

    lateinit var m: Model.AyaSearchBody
    lateinit var filterFragment: FilterFragment
    lateinit var searchFragment: SearchFragment
//    lateinit var historyFragment: HistoryFragment

    //  var filtermodel: Model.AyaSearchBody = Model.AyaSearchBody(q = arrayOf(""))
    var emptymodel: Model.AyaSearchResult? = null
    var emptyayaobject: Model.AyaObject? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        historyFragment = HistoryFragment.newInstance()
        searchFragment = SearchFragment.newInstance()
        filterFragment = FilterFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, searchFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

//        bottomBar.onItemSelected = {
//
//            if (it == 0) {
//                supportFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.container, searchFragment)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .commit()
//            }
//            if (it == 1) {
//                supportFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.container, historyFragment)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .commit()
//            }
//
//            if (it == 2) {
//                supportFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.container, filterFragment)
//                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                    .commit()
//            }
//        }
//        bottomBar.onItemReselected = {
//            // toast("Item $it re-selected")
//        }
//        bottomBar.setActiveItem(0)
    }

    fun Context.isNetworkReachable(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm?.activeNetworkInfo != null && cm?.activeNetworkInfo.isConnected
    }

    override fun onBackPressingFromChild() {
        onBackPressed()
    }


    override fun onFetchNewAyats(model: Model.AyaSearchBody, goback: Boolean) {

        val api = ApiFactory(this@MainActivity!!.applicationContext).myApi
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                val myDialog = indeterminateProgressDialog("Searching Query")
                myDialog.show()
                if (isNetworkReachable()) {
                    val r = api.search(params = model)
                    withContext(Dispatchers.IO) {
                        try {
                            if (r.isSuccessful) {
                                val ayaResult = r.body()?.ayas ?: arrayListOf()
                                withContext(Dispatchers.Main) {
                                    myDialog.dismiss()
                                    val sf = SearchResultFragment.newInstance(
                                        ayaResult,
                                        model = model
                                    )
                                    if (goback) {
                                        supportFragmentManager
                                            .beginTransaction()
                                            .replace(R.id.container, sf)
                                            .addToBackStack(searchFragment.toString())
                                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                            .commit()
                                    } else {
                                        supportFragmentManager
                                            .beginTransaction()
                                            .replace(R.id.container, sf)
                                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                            .commit()
                                    }
                                }
                            } else {
                                withContext(Dispatchers.Main) {
                                    myDialog.dismiss()
                                    alert(getString(R.string.server_error)) {
                                        okButton {
                                            it.dismiss()
                                        }
                                    }.show()

                                }
                                null
                            }
                        } catch (e: Exception) {
                            myDialog.dismiss()
                            Log.d("The Result for Error", e.message)
                            withContext(Dispatchers.Main) {
                                myDialog.dismiss()
                                alert(getString(R.string.server_error)) {
                                    okButton {
                                        it.dismiss()
                                    }
                                }.show()
                            }
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        myDialog.dismiss()
                        alert(getString(R.string.internet_error)) {
                            okButton {
                                it.dismiss()
                                finish()
                            }
                        }.show()

                    }
                }
            }
        }
    }
}
