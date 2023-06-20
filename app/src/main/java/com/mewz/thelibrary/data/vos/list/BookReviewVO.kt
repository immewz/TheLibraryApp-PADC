package com.mewz.thelibrary.data.vos.list

import com.google.gson.annotations.SerializedName

data class BookReviewVO(

    @SerializedName("book_review_link")
    val bookReviewLink: String?,

    @SerializedName("first_chapter_link")
    val firstChapterLink: String?,

    @SerializedName("sunday_review_link")
    val sundayReviewLink: String?,

    @SerializedName("article_chapter_link")
    val articleChapterLink: String?
)
