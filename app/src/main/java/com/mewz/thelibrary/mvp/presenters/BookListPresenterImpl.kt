package com.mewz.thelibrary.mvp.presenters

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.mewz.thelibrary.data.models.LibraryModel
import com.mewz.thelibrary.data.models.LibraryModelImpl
import com.mewz.thelibrary.data.vos.overview.BookVO
import com.mewz.thelibrary.mvp.views.BookListView

class BookListPresenterImpl: ViewModel(), BookListPresenter {

    private var mView:BookListView? = null
    private val mLibraryModel: LibraryModel = LibraryModelImpl

    override fun initView(view: BookListView) {
        mView = view
    }

    override fun onUiReadyForBookList(owner: LifecycleOwner, listName: String) {
        mLibraryModel.getBookList(listName = listName, onFailure = {
            mView?.showError(it)
        })?.observe(owner) {
            mView?.showBookList(it)
        }
    }

    override fun deleteTheWholeBookList() {
        mLibraryModel.deleteTheWholeBookList()
    }

    override fun onTapBack() {
        mView?.navigateToBackScreen()
    }

    override fun onUiReady(owner: LifecycleOwner) {
    }

    override fun onTapBook(bookName: String, listId: Int) {
        mView?.navigateToBookDetailScreen(bookName,listId)
    }

    override fun onTapBookOption(book: BookVO?, listId: Int, listName: String) {
        mView?.onTapBookOption()
    }
}