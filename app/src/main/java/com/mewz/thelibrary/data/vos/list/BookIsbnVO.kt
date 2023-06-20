package com.mewz.thelibrary.data.vos.list

import com.google.gson.annotations.SerializedName

data class BookIsbnVO(

    @SerializedName("isbn10")
    val isbn10: String?,

    @SerializedName("isbn13")
    val isbn13: String?
)
