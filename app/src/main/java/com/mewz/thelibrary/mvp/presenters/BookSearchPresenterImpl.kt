package com.mewz.thelibrary.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.mewz.thelibrary.data.models.LibraryModel
import com.mewz.thelibrary.data.models.LibraryModelImpl
import com.mewz.thelibrary.mvp.views.BookSearchView

class BookSearchPresenterImpl: ViewModel(), BookSearchPresenter {

    private var mView:BookSearchView? = null
    private val mLibraryModel: LibraryModel = LibraryModelImpl

    override fun initView(view: BookSearchView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
    }
}