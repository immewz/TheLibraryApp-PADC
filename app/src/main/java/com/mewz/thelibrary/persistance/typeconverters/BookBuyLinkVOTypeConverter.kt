package com.mewz.thelibrary.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mewz.thelibrary.data.vos.overview.BookBuyLinkVO

class BookBuyLinkVOTypeConverter {
    @TypeConverter
    fun toString(buyLinkList: List<BookBuyLinkVO>?) :String {
        return Gson().toJson(buyLinkList)
    }

    @TypeConverter
    fun toBuyLink(jsonString:String) : List<BookBuyLinkVO>? {
        val buyLinkType = object : TypeToken<List<BookBuyLinkVO>?>(){}.type
        return Gson().fromJson(jsonString,buyLinkType)
    }
}