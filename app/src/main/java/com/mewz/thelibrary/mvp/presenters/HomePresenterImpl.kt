package com.mewz.thelibrary.mvp.presenters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.mewz.thelibrary.data.models.LibraryModel
import com.mewz.thelibrary.data.models.LibraryModelImpl
import com.mewz.thelibrary.data.vos.overview.BookVO
import com.mewz.thelibrary.mvp.views.HomeView

class HomePresenterImpl: ViewModel(), HomePresenter {

    private var mView:HomeView? = null
    private val mLibraryModel: LibraryModel = LibraryModelImpl

    override fun initView(view: HomeView) {
        mView = view
    }

    override fun insertBookIntoLibrary(book: BookVO?) {
        mLibraryModel.insertBookIntoLibrary(book)
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mLibraryModel.getBookOverview { error ->
            mView?.showError(error)
        }?.observe(owner) {
            mView?.showFirstCategory(it ?: listOf())
        }

        mLibraryModel.getBookOverview { error ->
            mView?.showError(error)
        }?.observe(owner) {
            mView?.showSecondCategory(it ?: listOf())
        }

        mLibraryModel.getBookOverview { error ->
            mView?.showError(error)
        }?.observe(owner) {
            mView?.showThirdCategory(it ?: listOf())
        }

        mLibraryModel.getAllBooksFromLibrary()?.observe(owner) {
            mView?.showBooksForBanner(it ?: listOf())
        }
    }

    override fun onTapGoToBookListScreen(listName: String, listId: Int) {
        mView?.navigateToBookListScreen(listName, listId)
    }

    override fun onTapBannerBook(bookName: String, listId: Int) {
        mView?.navigateToBookDetailScreen(bookName, listId)
    }

    override fun onTapBannerBookOption(book: BookVO?, listId: Int, listName: String) {
        mView?.onTapBookOption(book, listId, listName)
    }

    override fun onTapBook(bookName: String, listId: Int) {
        mView?.navigateToBookDetailScreen(bookName,listId)
    }

    override fun onTapBookOption(book: BookVO?, listId: Int, bookName: String) {
        mView?.onTapBookOption(book, listId, bookName)
    }
}