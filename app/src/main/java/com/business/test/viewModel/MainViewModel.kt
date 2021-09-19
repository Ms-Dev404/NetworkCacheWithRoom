package com.business.test.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.business.test.interMediator.FetchingState
import com.business.test.interMediator.Repository

import com.business.test.model.NasaPost

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(private var repository: Repository):ViewModel() {

    fun getPosts(key:String,size:String) = liveData(Dispatchers.IO) {
        emit(FetchingState.Loading)
        try {

            val response=repository.getNasaPost(key,size)

            if(response.isNotEmpty()){

                insertCacheData(response)


            }
            emit(FetchingState.Success(response))

        } catch (exception: Exception) {

            emit(FetchingState.Error(exception))
            delay(1500)
            emit(FetchingState.Cached(repository.getCachedPosts()))
        }

    }

    private fun insertCacheData(nasaPosts:List< NasaPost>){

       viewModelScope.launch(Dispatchers.IO) {

         repository.insertData(nasaPosts)

       }

    }




}