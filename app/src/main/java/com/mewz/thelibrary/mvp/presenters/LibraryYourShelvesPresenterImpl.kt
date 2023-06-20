package com.mewz.thelibrary.mvp.presenters

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.mewz.thelibrary.data.models.LibraryModel
import com.mewz.thelibrary.data.models.LibraryModelImpl
import com.mewz.thelibrary.mvp.views.LibraryYourShelvesView

class LibraryYourShelvesPresenterImpl: ViewModel(), LibraryYourShelvesPresenter {

    private var mView: LibraryYourShelvesView? = null
    private val mLibraryModel: LibraryModel = LibraryModelImpl

    override fun initView(view: LibraryYourShelvesView) {
        mView = view
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mLibraryModel.getShelfList()?.observe(owner) {
            mView?.showShelfList(it)
            Log.d("impl","${mView?.showShelfList(it)}")
        }
    }

    override fun onTapShelvesBookList(shelfId: Int) {
        mView?.navigateToShelfDetailScreen(shelfId)
    }

}