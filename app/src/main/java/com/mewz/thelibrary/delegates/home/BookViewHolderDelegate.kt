package com.mewz.thelibrary.delegates.home

import com.mewz.thelibrary.data.vos.overview.BookVO

interface BookViewHolderDelegate {
    fun onTapBook(bookName:String,listId:Int)
    fun onTapBookOption(book: BookVO?, listId: Int, listName: String)
}