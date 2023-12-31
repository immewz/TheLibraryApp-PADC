package com.mewz.thelibrary.data.vos.google

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.mewz.thelibrary.persistance.typeconverters.AccessInfoTypeConverter
import com.mewz.thelibrary.persistance.typeconverters.SaleInfoTypeConverter
import com.mewz.thelibrary.persistance.typeconverters.SearchInfoTypeConverter
import com.mewz.thelibrary.persistance.typeconverters.VolumeInfoTypeConverter

@Entity("google_book_list_table")
@TypeConverters(
    AccessInfoTypeConverter::class,
    SaleInfoTypeConverter::class,
    SearchInfoTypeConverter::class,
    VolumeInfoTypeConverter::class
)
data class GoogleBookVO(

    @SerializedName("accessInfo")
    @ColumnInfo("accessInfo")
    val accessInfo: AccessInfo?,

    @SerializedName("etag")
    @ColumnInfo("etag")
    val etag: String?,

    @SerializedName("id")
    @PrimaryKey
    val id: String,

    @SerializedName("kind")
    @ColumnInfo("kind")
    val kind: String?,

    @SerializedName("saleInfo")
    @ColumnInfo("saleInfo")
    val saleInfo: SaleInfo?,

    @SerializedName("searchInfo")
    @ColumnInfo("searchInfo")
    val searchInfo: SearchInfo?,

    @SerializedName("selfLink")
    @ColumnInfo("selfLink")
    val selfLink: String?,

    @SerializedName("volumeInfo")
    @ColumnInfo("volumeInfo")
    val volumeInfo: VolumeInfo?
)
