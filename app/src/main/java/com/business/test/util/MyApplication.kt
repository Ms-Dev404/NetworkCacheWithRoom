package com.business.test.util

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.room.withTransaction
import com.business.test.interMediator.Repository
import com.business.test.network.ApiService
import com.business.test.network.RetrofitClient
import com.business.test.room.CacheDao
import com.business.test.room.MyCacheDb


class MyApplication:Application() {

    private var cacheDb:MyCacheDb?=null
    private var apiService:ApiService?=null

    companion object{

        var repositoryInstance:Repository?=null


    }

    override fun onCreate() {
        super.onCreate()

            apiService=apiService?:RetrofitClient().getRetroClient().create(ApiService::class.java)
           cacheDb=cacheDb?:MyCacheDb.initCacheDb(applicationContext)
           repositoryInstance=repositoryInstance?: Repository(apiService!!,cacheDb!!)


    }

}