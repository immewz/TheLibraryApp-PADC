package com.mewz.thelibrary.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mewz.thelibrary.data.vos.google.AccessInfo

class AccessInfoTypeConverter {
    @TypeConverter
    fun toString(accessInfo: AccessInfo?) :String {
        return Gson().toJson(accessInfo)
    }

    @TypeConverter
    fun toAccessInfo(jsonString:String) : AccessInfo? {
        val accessInfoType = object : TypeToken<AccessInfo?>(){}.type
        return Gson().fromJson(jsonString,accessInfoType)
    }
}