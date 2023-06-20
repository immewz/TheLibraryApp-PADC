package com.mewz.thelibrary.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mewz.thelibrary.data.vos.overview.BookVO

class MutableBookVOTypeConverter {
    @TypeConverter
    fun toString(bookList: MutableList<BookVO>?) :String {
        return Gson().toJson(bookList)
    }

    @TypeConverter
    fun toBookVO(jsonString:String) : MutableList<BookVO>? {
        val bookListType = object : TypeToken<MutableList<BookVO>?>(){}.type
        return Gson().fromJson(jsonString,bookListType)
    }
}