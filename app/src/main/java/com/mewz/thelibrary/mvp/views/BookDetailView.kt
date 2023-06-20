package com.mewz.thelibrary.mvp.views

import com.mewz.thelibrary.data.vos.SearchBookVO
import com.mewz.thelibrary.data.vos.list.BookDetailVO
import com.mewz.thelibrary.data.vos.overview.BookCategoryVO
import com.mewz.thelibrary.data.vos.overview.BookVO

interface BookDetailView: IBaseView {

    fun getCategoryByName(category: BookCategoryVO)
    fun getBookFromBookList(bookDetail: BookDetailVO)
    fun getAllBooksFromLibrary(bookList: List<BookVO>)


    fun navigateToRatingAndReviewScreen()
    fun navigateToAboutBookScreen()
    fun navigateToBackScreen()
    fun showSearchBook(bookList: List<SearchBookVO>)
}