package com.mewz.thelibrary.data.vos.list

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.mewz.thelibrary.persistance.typeconverters.BookDetailVOTypeConverter
import com.mewz.thelibrary.persistance.typeconverters.BookIsbnVOTypeConverter
import com.mewz.thelibrary.persistance.typeconverters.BookReviewVOTypeConverter

@Entity("book_list_table")
@TypeConverters(
    BookIsbnVOTypeConverter::class,
    BookDetailVOTypeConverter::class,
    BookReviewVOTypeConverter::class
)
data class BookListVO(

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @SerializedName("list_name")
    @ColumnInfo("list_name")
    val listName: String?,

    @SerializedName("display_name")
    @ColumnInfo("display_name")
    val displayName: String?,

    @SerializedName("bestsellers_date")
    @ColumnInfo("bestsellers_date")
    val bestsellersDate: String?,

    @SerializedName("published_date")
    @ColumnInfo("published_date")
    val publishedDate: String?,

    @SerializedName("rank")
    @ColumnInfo("rank")
    val rank: Int?,

    @SerializedName("rank_last_week")
    @ColumnInfo("rank_last_week")
    val rankLastWeek: Int?,

    @SerializedName("weeks_on_list")
    @ColumnInfo("weeks_on_list")
    val weeksOnList: Int?,

    @SerializedName("asterisk")
    @ColumnInfo("asterisk")
    val asterisk: Int?,

    @SerializedName("dagger")
    @ColumnInfo("dagger")
    val dagger: Int?,

    @SerializedName("amazon_product_url")
    @ColumnInfo("amazon_product_url")
    val amazonProductUrl: String?,

    @SerializedName("isbns")
    @ColumnInfo("isbns")
    val isbns: List<BookIsbnVO>?,

    @SerializedName("book_details")
    @ColumnInfo("book_details")
    val bookDetails: List<BookDetailVO>?,

    @SerializedName("reviews")
    @ColumnInfo("reviews")
    val reviews: List<BookReviewVO>?
)
