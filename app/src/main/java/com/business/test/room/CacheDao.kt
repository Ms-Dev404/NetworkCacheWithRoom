package com.business.test.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.business.test.model.NasaPost

@Dao
interface CacheDao {



    @Insert(onConflict =OnConflictStrategy.REPLACE)
    suspend fun insertNasaDetails(nasaPost: List<NasaPost>)

    @Query("SELECT * FROM nasatable")
    fun getCachedPosts():List<NasaPost>

    @Query("DELETE FROM nasatable")
    suspend fun deleteNasaCache()


}
