package com.business.test.interMediator

import androidx.lifecycle.LiveData
import androidx.room.withTransaction
import com.business.test.model.NasaPost
import com.business.test.network.ApiService
import com.business.test.room.MyCacheDb


class Repository(private val apiService: ApiService,private val cacheDb: MyCacheDb){

    private val cacheDao=cacheDb.getCacheDao()

    suspend fun getNasaPost(key:String,size:String)=apiService.getNasaPost(key,size)


    suspend fun insertData(nasaPosts: List<NasaPost>){

        cacheDb.withTransaction {

            cacheDao.deleteNasaCache()
            cacheDao.insertNasaDetails(nasaPosts)
        }

    }

 fun getCachedPosts():List<NasaPost>{

     cacheDao.let {

         return it.getCachedPosts()
     }
 }



}