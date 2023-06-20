package com.mewz.thelibrary.mvp.presenters

import com.mewz.thelibrary.adapters.ShelvesListAdapter
import com.mewz.thelibrary.delegates.library.ShelvesListViewHolderDelegate
import com.mewz.thelibrary.mvp.views.LibraryYourShelvesView

interface LibraryYourShelvesPresenter: IBasePresenter, ShelvesListViewHolderDelegate {
    fun initView(view: LibraryYourShelvesView)
}