package com.mewz.thelibrary.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mewz.thelibrary.data.vos.overview.BookVO

class BookVOTypeConverter {
    @TypeConverter
    fun toString(bookList: List<BookVO>?) :String {
        return Gson().toJson(bookList)
    }

    @TypeConverter
    fun toBookVO(jsonString:String) : List<BookVO>? {
        val bookListType = object : TypeToken<List<BookVO>?>(){}.type
        return Gson().fromJson(jsonString,bookListType)
    }
}