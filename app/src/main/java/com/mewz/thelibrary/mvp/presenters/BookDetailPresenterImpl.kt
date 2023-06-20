package com.mewz.thelibrary.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.mewz.thelibrary.data.models.LibraryModel
import com.mewz.thelibrary.data.models.LibraryModelImpl
import com.mewz.thelibrary.data.vos.overview.BookVO
import com.mewz.thelibrary.mvp.views.BookDetailView

class BookDetailPresenterImpl: ViewModel(), BookDetailPresenter {

    private var mView: BookDetailView? = null
    private val mLibraryModel: LibraryModel = LibraryModelImpl

    override fun initView(view: BookDetailView) {
        mView = view
    }

    override fun onUiReadyForBookDetail(
        owner: LifecycleOwner,
        listName: String,
        listId: Int,
        previousPlace: String
    ) {
        when (previousPlace) {
            "HomeFragment" -> {
                mLibraryModel.getCategoryByListId(listId = listId)?.observe(owner) {
                    it?.let { category ->
                        mView?.getCategoryByName(category)
                    }
                }
            }

            "BookListActivity" -> {
                mLibraryModel.getBookFromBookListById(listId)?.observe(owner) {
                    it.bookDetails?.get(0)?.let { bookDetail ->
                        mView?.getBookFromBookList(bookDetail)
                    }
                }
            }

            "BookSearchActivity" -> {
                mLibraryModel.getSearchBookList()?.observe(owner) {
                    mView?.showSearchBook(it)
                }
            }

            else -> {
                mLibraryModel.getAllBooksFromLibrary()?.observe(owner) {
                    mView?.getAllBooksFromLibrary(it)
                }
            }
        }
    }

    override fun insertBookIntoLibrary(book: BookVO?) {
        mLibraryModel.insertBookIntoLibrary(book)
    }

    override fun onTapBack() {
        mView?.navigateToBackScreen()
    }

    override fun onTapAboutBook() {
        mView?.navigateToAboutBookScreen()
    }

    override fun onTapRatingAndReview() {
        mView?.navigateToRatingAndReviewScreen()
    }

    override fun onUiReady(owner: LifecycleOwner) {
    }
}