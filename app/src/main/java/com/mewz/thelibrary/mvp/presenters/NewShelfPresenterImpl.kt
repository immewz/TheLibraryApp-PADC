package com.mewz.thelibrary.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.mewz.thelibrary.data.models.LibraryModel
import com.mewz.thelibrary.data.models.LibraryModelImpl
import com.mewz.thelibrary.data.vos.ShelfVO
import com.mewz.thelibrary.mvp.views.NewShelfView

class NewShelfPresenterImpl: ViewModel(), NewShelfPresenter{

    private var mView:NewShelfView? = null
    private val mLibraryModel: LibraryModel = LibraryModelImpl

    override fun initView(view: NewShelfView) {
        mView = view
    }

    override fun insertShelf(shelf: ShelfVO) {
        mLibraryModel.insertShelf(shelf)
    }

    override fun onTapBack() {
        mView?.navigateToBackScreen()
    }

    override fun onUiReady(owner: LifecycleOwner) {
    }
}