package com.business.test.interMediator

import com.business.test.model.NasaPost
import java.lang.Exception

sealed class FetchingState<out T>(){

  object Loading

  data class Success(var response: ArrayList<NasaPost>):FetchingState<ArrayList<NasaPost>>()

  data class Error(var exception: Exception):FetchingState<Exception>()

  data class Cached(var cacheList: List<NasaPost>):FetchingState<List<NasaPost>>()

}
