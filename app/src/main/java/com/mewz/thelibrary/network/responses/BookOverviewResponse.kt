package com.mewz.thelibrary.network.responses

import com.google.gson.annotations.SerializedName
import com.mewz.thelibrary.data.vos.overview.BookOverviewVO

data class BookOverviewResponse(

    @SerializedName("status")
    val status: String?,

    @SerializedName("copyright")
    val copyright: String?,

    @SerializedName("num_results")
    val numResults: Int?,

    @SerializedName("results")
    val results: BookOverviewVO?
)