package com.mewz.thelibrary.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mewz.thelibrary.data.vos.list.BookDetailVO

class BookDetailVOTypeConverter {
    @TypeConverter
    fun toString(bookDetailList: List<BookDetailVO>?): String {
        return Gson().toJson(bookDetailList)
    }

    @TypeConverter
    fun toBookDetailVO(jsonString: String): List<BookDetailVO>? {
        val bookDetailType = object : TypeToken<List<BookDetailVO>?>() {}.type
        return Gson().fromJson(jsonString, bookDetailType)
    }
}