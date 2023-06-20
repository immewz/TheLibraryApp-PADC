package com.mewz.thelibrary.mvp.views

import com.mewz.thelibrary.data.vos.list.BookListVO

interface BookListView: IBaseView {

    fun showBookList(bookList: List<BookListVO>)

    fun navigateToBookDetailScreen(bookName:String,listId:Int)
    fun onTapBookOption()
    fun navigateToBackScreen()
}