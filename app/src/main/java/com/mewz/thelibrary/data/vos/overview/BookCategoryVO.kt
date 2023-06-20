package com.mewz.thelibrary.data.vos.overview

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.mewz.thelibrary.persistance.typeconverters.AnyTypeConverter
import com.mewz.thelibrary.persistance.typeconverters.BookVOTypeConverter

@Entity("book_category_table")
@TypeConverters(
    AnyTypeConverter::class,
    BookVOTypeConverter::class
)
data class BookCategoryVO(

    @SerializedName("list_id")
    @PrimaryKey
    val listId: Int = 0,

    @SerializedName("list_name")
    @ColumnInfo("list_name")
    val listName: String?,

    @SerializedName("list_name_encoded")
    @ColumnInfo("list_name_encoded")
    val listNameEncoded: String?,

    @SerializedName("display_name")
    @ColumnInfo("display_name")
    val displayName: String?,

    @SerializedName("updated")
    @ColumnInfo("updated")
    val updated: String?,

    @SerializedName("list_image")
    @ColumnInfo("list_image")
    val listImage: Any?,

    @SerializedName("list_image_width")
    @ColumnInfo("list_image_width")
    val listImageWidth: Any?,

    @SerializedName("list_image_height")
    @ColumnInfo("list_image_height")
    val listImageHeight: Any?,

    @SerializedName("books")
    @ColumnInfo("books")
    val books: List<BookVO>?

)
