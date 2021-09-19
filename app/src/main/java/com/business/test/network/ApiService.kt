package com.business.test.network

import com.business.test.model.NasaPost
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {



@GET(Urls.GET_LIST)
suspend fun getNasaPost(@Query("api_key")key:String,@Query("count")count:String):ArrayList<NasaPost>

}