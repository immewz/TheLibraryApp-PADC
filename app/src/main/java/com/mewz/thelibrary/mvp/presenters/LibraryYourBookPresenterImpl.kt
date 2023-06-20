package com.mewz.thelibrary.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.mewz.thelibrary.data.models.LibraryModel
import com.mewz.thelibrary.data.models.LibraryModelImpl
import com.mewz.thelibrary.data.vos.overview.BookVO
import com.mewz.thelibrary.fragments.LibraryYourBookFragment
import com.mewz.thelibrary.mvp.views.LibraryYourBookView

class LibraryYourBookPresenterImpl: ViewModel(), LibraryYourBookPresenter {

    private var mView: LibraryYourBookView? = null
    private val mLibraryModel: LibraryModel = LibraryModelImpl

    private var mBookList: List<BookVO> = listOf()

    override fun initView(view: LibraryYourBookView) {
        mView = view
    }

    override fun onUiReadyForListName(owner: LifecycleOwner, listName: String) {
        mLibraryModel.getBookListByListName(listName)?.observe(owner) {
            mView?.showBookListByListName(it ?: listOf())
        }
    }

    override fun deleteBookByTitle(title: String) {
        mLibraryModel.deleteBookByTitle(title)
    }

    override fun sortByTitle(): List<BookVO>? {
        return mBookList.sortedBy { book ->
            book.title
        }
    }

    override fun sortByAuthor(): List<BookVO>? {
        return mBookList.sortedBy { book ->
            book.author
        }
    }

    override fun onTapView() {
        mView?.showBottomSheetDialogForView()
    }

    override fun onTapSort() {
        mView?.showBottomSheetDialogForSorting()
    }

    override fun onTapClose() {
        mView?.onTapCloseAllChip()
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mLibraryModel.getAllBooksFromLibrary()?.observe(owner) {
            mBookList = it
            mView?.showBooksInLibrary(it ?: listOf())
        }
    }

    override fun onTapChip(listName: String) {
        mView?.onTapChip(listName)
    }
}