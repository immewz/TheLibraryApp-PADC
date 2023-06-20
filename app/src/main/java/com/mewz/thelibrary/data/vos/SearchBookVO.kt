package com.mewz.thelibrary.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_table")
data class SearchBookVO(

    @PrimaryKey
    var title:String,

    @ColumnInfo("author")
    var author:String?,

    @ColumnInfo("description")
    var description: String?,

    @ColumnInfo("image")
    var image: String?,

    )
