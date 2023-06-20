package com.mewz.thelibrary.mvp.views

import com.mewz.thelibrary.data.vos.overview.BookCategoryVO
import com.mewz.thelibrary.data.vos.overview.BookVO

interface HomeView: IBaseView {

    fun showBooksForBanner(bookList:List<BookVO>?)

    fun showFirstCategory(category:List<BookCategoryVO>?)
    fun showSecondCategory(category:List<BookCategoryVO>?)
    fun showThirdCategory(category:List<BookCategoryVO>?)

    fun navigateToBookDetailScreen(bookName:String,listId:Int)
    fun navigateToBookListScreen(listName:String,listId: Int)

    fun onTapBookOption(book: BookVO?,listId: Int,listName: String)
}