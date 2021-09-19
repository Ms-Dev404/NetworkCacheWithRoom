package com.business.test.room

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.business.test.model.NasaPost

@Database(entities = [NasaPost::class], version = 2,exportSchema = false)



abstract class MyCacheDb:RoomDatabase() {

    companion object{
        @Volatile
         var INSTANCE:MyCacheDb?=null

        fun initCacheDb(context: Context):MyCacheDb{

           if(INSTANCE==null){

             INSTANCE= Room.databaseBuilder(context,MyCacheDb::class.java,"CacheDb").fallbackToDestructiveMigration().addMigrations(migration).build()
           }
         return INSTANCE!!
        }
    }

    abstract fun getCacheDao():CacheDao

    object migration : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `NasaTable_temp` (`title` TEXT NOT NULL, `date` TEXT NOT NULL, `explanation` TEXT NOT NULL, `hdurl` TEXT,url TEXT, PRIMARY KEY(`title`))")
            database.execSQL("INSERT INTO NasaTable_temp(title, date, explanation, hdurl,url) SELECT title, date, explanation, hdurl,url FROM NasaTable")
            database.execSQL("DROP TABLE NasaTable")
            database.execSQL("ALTER TABLE NasaTable_temp RENAME TO NasaTable")
        }
    }
}