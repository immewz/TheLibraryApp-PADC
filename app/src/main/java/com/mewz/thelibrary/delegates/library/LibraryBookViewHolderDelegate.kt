package com.mewz.thelibrary.delegates.library

import com.mewz.thelibrary.data.vos.overview.BookVO

interface LibraryBookViewHolderDelegate {
    fun onTapLibraryBook(bookName:String,listId:Int)
    fun onTapLibraryBookOption(book: BookVO)
}