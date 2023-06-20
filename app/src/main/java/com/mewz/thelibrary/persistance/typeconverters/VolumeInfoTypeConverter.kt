package com.mewz.thelibrary.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mewz.thelibrary.data.vos.google.VolumeInfo

class VolumeInfoTypeConverter {
    @TypeConverter
    fun toString(volumeInfo: VolumeInfo?) :String {
        return Gson().toJson(volumeInfo)
    }

    @TypeConverter
    fun toVolumeInfo(jsonString:String) : VolumeInfo? {
        val volumeInfoType = object : TypeToken<VolumeInfo?>(){}.type
        return Gson().fromJson(jsonString,volumeInfoType)
    }
}