package com.mewz.thelibrary.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mewz.thelibrary.data.vos.list.BookReviewVO

class BookReviewVOTypeConverter {
    @TypeConverter
    fun toString(reviewList: List<BookReviewVO>?): String {
        return Gson().toJson(reviewList)
    }

    @TypeConverter
    fun toBookReviewVO(jsonString: String): List<BookReviewVO>? {
        val bookReviewType = object : TypeToken<List<BookReviewVO>?>() {}.type
        return Gson().fromJson(jsonString, bookReviewType)
    }
}