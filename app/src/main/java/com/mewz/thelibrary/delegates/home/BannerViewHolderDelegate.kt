package com.mewz.thelibrary.delegates.home

import com.mewz.thelibrary.data.vos.overview.BookVO

interface BannerViewHolderDelegate {
    fun onTapBannerBook(bookName:String, listId:Int)
    fun onTapBannerBookOption(book: BookVO?, listId: Int, listName: String)
}