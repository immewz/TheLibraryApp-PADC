package com.mewz.thelibrary.data.vos.list

import com.google.gson.annotations.SerializedName

data class BookDetailVO(

    @SerializedName("title")
    val title: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("contributor")
    val contributor: String?,

    @SerializedName("author")
    val author: String?,

    @SerializedName("contributor_note")
    val contributorNote: String?,

    @SerializedName("price")
    val price: String?,

    @SerializedName("age_group")
    val ageGroup: String?,

    @SerializedName("publisher")
    val publisher: String?,

    @SerializedName("primary_isbn13")
    val primaryIsbn13: String?,

    @SerializedName("primary_isbn10")
    val primaryIsbn10: String?
)
