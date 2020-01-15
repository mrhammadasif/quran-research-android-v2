package com.nitroxis.app.quranresearch

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.nitroxis.app.quranresearch.Fragment.FilterFragment
import com.nitroxis.app.quranresearch.Fragment.HistoryFragment
import com.nitroxis.app.quranresearch.Fragment.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import pub.devrel.easypermissions.EasyPermissions
import java.security.AccessController.getContext


class MainActivity : AppCompatActivity(), FilterFragment.OnFragmentInteractionListener,
    HistoryFragment.OnFragmentInteractionListener, SearchFragment.OnFragmentInteractionListener{

    lateinit var filterFragment: FilterFragment
    lateinit var searchFragment: SearchFragment
    lateinit var historyFragment: HistoryFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottomBar.onItemSelected = {
            //  toast(it.toString())
            // status.text = "Item $it selected"
            if (it == 1) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, historyFragment)
                    .addToBackStack(historyFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            if (it == 0) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, searchFragment)
                    .addToBackStack(searchFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            if (it == 2) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, filterFragment)
                    .addToBackStack(filterFragment.toString())
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

        }



        bottomBar.onItemReselected = {
            toast("Item $it re-selected")

        }
        bottomBar.setActiveItem(1)


        historyFragment = HistoryFragment.newInstance()
        searchFragment = SearchFragment.newInstance()
        filterFragment = FilterFragment.newInstance()


    }

    override fun onFragmentInteraction(uri: Uri) {

    }


}
