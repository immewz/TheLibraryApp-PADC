package com.mewz.thelibrary.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mewz.thelibrary.data.vos.list.BookIsbnVO

class BookIsbnVOTypeConverter {
    @TypeConverter
    fun toString(isbnList: List<BookIsbnVO>?): String {
        return Gson().toJson(isbnList)
    }

    @TypeConverter
    fun toBookIsbnVO(jsonString: String): List<BookIsbnVO>? {
        val bookIsbnType = object : TypeToken<List<BookIsbnVO>?>() {}.type
        return Gson().fromJson(jsonString, bookIsbnType)
    }
}