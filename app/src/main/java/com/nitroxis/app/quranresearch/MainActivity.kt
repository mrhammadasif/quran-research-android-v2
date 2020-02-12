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
import com.nitroxis.app.quranresearch.Utils.Model
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.*


class MainActivity : AppCompatActivity(), FilterFragment.OnFragmentInteractionListener,
    HistoryFragment.OnFragmentInteractionListener, SearchFragment.OnFragmentInteractionListener,
    SearchResultFragment.OnFragmentInteractionListener {

    lateinit var filterFragment: FilterFragment
    lateinit var searchFragment: SearchFragment
    lateinit var historyFragment: HistoryFragment
    //  var filtermodel: Model.AyaSearchBody = Model.AyaSearchBody(q = arrayOf(""))
      var emptymodel: Model.AyaSearchResult? =null


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
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            if (it == 1) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, historyFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

            if (it == 2) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, filterFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
        }
        bottomBar.onItemReselected = {
            // toast("Item $it re-selected")
        }
        bottomBar.setActiveItem(0)
    }

    fun Context.isNetworkReachable(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
    }

    override fun onFragmentInteraction(uri: Uri) {

    }


    override fun onFetchNewAyats(model: Model.AyaSearchBody) {


        val api = ApiFactory(this@MainActivity!!.applicationContext).myApi
        GlobalScope.launch {

            withContext(Dispatchers.Main) {
                val myDialog = indeterminateProgressDialog("Searching Query")
                myDialog.show()
                if (isNetworkReachable()) {
                    withContext(Dispatchers.IO) {
                        try {

                            Log.d("123456",model.toString())
                            val r = api.search(params = model)
                            if (r.isSuccessful && r.code() == 200) {
                                withContext(Dispatchers.Main) {
                                    myDialog.dismiss()
                                    val ayaresult = r.body()!!
                                    val sf =
                                        SearchResultFragment.newInstance(
                                            ayaresult.ayas,
                                            model = model
                                        )
                                    supportFragmentManager
                                        .beginTransaction()
                                        .replace(R.id.container, sf)
                                        .addToBackStack(null)
                                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                        .commit()
                                    Log.d("Model of new Ayat", model.toString())
                                    Log.d("response1", r.code().toString())
                                    Log.d("response1", r.body().toString())
                                    Log.d("response", r.message())
                                    Log.d("error", r.message())
                                    Log.d("response", r.message())

                                }

                            } else if (r.code() == 204) {
                                withContext(Dispatchers.Main) {
                                    myDialog.dismiss()
                                    Log.d("responsecode",r.code().toString())
                                    alert("No Query Found For This Keyword") {
                                        okButton {
                                            it.dismiss()
                                        }
                                    }.show()
                                }
                                null

                            } else {
                                myDialog.dismiss()
                                Log.d(
                                    "The Result for Error", r.errorBody()?.string().toString()
                                )
                                throw Exception(r.errorBody()?.string())
                            }
                        } catch (e: Exception) {
                            myDialog.dismiss()
                            Log.d("The Result for Error", e.message)
                            withContext(Dispatchers.Main) {
                                myDialog.dismiss()
                                alert(e.message.toString()) {
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
                        alert("No Internet Connection.Please Check Your Internet Connection And Try Again!") {
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
