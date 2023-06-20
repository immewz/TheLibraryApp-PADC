package com.mewz.thelibrary.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.mewz.thelibrary.delegates.home.BookViewHolderDelegate
import com.mewz.thelibrary.mvp.views.BookListView

interface BookListPresenter: IBasePresenter, BookViewHolderDelegate {

    fun initView(view: BookListView)
    fun onUiReadyForBookList(owner: LifecycleOwner, listName:String)
    fun deleteTheWholeBookList()
    fun onTapBack()
}