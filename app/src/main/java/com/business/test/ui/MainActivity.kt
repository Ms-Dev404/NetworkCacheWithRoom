package com.business.test.ui

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.business.test.R
import com.business.test.databinding.ActivityMainBinding
import com.business.test.interMediator.FactoryVm
import com.business.test.interMediator.FetchingState
import com.business.test.model.NasaPost
import com.business.test.util.MyApplication.Companion.repositoryInstance
import com.business.test.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first


class MainActivity : AppCompatActivity() {

    private lateinit var factoryVm: FactoryVm
    private lateinit var mainViewModel: MainViewModel
    private lateinit var pbd: ProgressDialog
    private var cm: ConnectivityManager? = null
    private var nF: NetworkInfo? = null
    private  var nasaPosts= ArrayList<NasaPost>()
    private lateinit var  itemsAdapter:ItemsAdapter
    val flow:Flow<NasaPost>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pbd = ProgressDialog(this)
        pbd.setMessage("Fetching data...")
        pbd.setCancelable(false)
        factoryVm = FactoryVm(repositoryInstance!!)
        itemsAdapter=ItemsAdapter(nasaPosts)

        binding.apply {

            rvCategory.layoutManager=LinearLayoutManager(binding.root.context)
            rvCategory.adapter=itemsAdapter
        }


        mainViewModel = ViewModelProvider(this, factoryVm)[MainViewModel::class.java]
        supportActionBar?.hide()
        var tvHeader=binding.appBar.findViewById<AppCompatTextView>(R.id.tv_header)
        tvHeader.text=getString(R.string.listing_header_name)
        fetchDataFromServer()

        if (!checkConnectivity()) {

            showSnakeBar(binding)

        }

    }



    private fun fetchDataFromServer() {

        mainViewModel.getPosts("DEMO_KEY","10").observe(this, { stateObserver ->

            when (stateObserver) {

                is FetchingState.Loading -> {
                    pbd.show()
                }

                is FetchingState.Success -> {
                    pbd.cancel()
                    Toast.makeText(
                        this,
                        "Network fetch success!",
                        Toast.LENGTH_SHORT
                    ).show()

                   if(stateObserver.response.isNotEmpty()){
                     if(nasaPosts.isNotEmpty()){

                         nasaPosts.clear()}

                     nasaPosts.addAll(stateObserver.response)

                     itemsAdapter.notifyDataSetChanged()

                   }

                }

                is FetchingState.Error -> {

                    Toast.makeText(
                        this,
                        "fetching from dataBase!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is FetchingState.Cached -> {
                    pbd.cancel()

                    if(stateObserver.cacheList.isNotEmpty()&&nasaPosts.isEmpty()){

                        nasaPosts.clear()
                        nasaPosts.addAll(stateObserver.cacheList)
                        itemsAdapter.notifyDataSetChanged()

                    }
                }
            }
        })
    }

    private fun checkConnectivity(): Boolean {
        cm = cm ?: getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        nF = cm!!.activeNetworkInfo
        return nF?.isConnectedOrConnecting == true
    }

  private fun showSnakeBar(context: ActivityMainBinding){

      Snackbar.make(context.root,"Device Offline",Snackbar.LENGTH_SHORT).show()

  }

}