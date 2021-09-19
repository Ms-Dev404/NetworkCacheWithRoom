package com.business.test.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "NasaTable")
data class NasaPost(
    @PrimaryKey val title:String,

    var date:String,

    var explanation:String,

    var hdurl:String?,

    var url:String?

    )


