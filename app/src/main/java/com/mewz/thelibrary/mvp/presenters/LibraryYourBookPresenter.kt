package com.mewz.thelibrary.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.mewz.thelibrary.data.vos.overview.BookVO
import com.mewz.thelibrary.delegates.library.LibraryChipViewHolderDelegate
import com.mewz.thelibrary.mvp.views.LibraryYourBookView

interface LibraryYourBookPresenter: IBasePresenter, LibraryChipViewHolderDelegate {

    fun initView(view: LibraryYourBookView)
    fun onUiReadyForListName(owner: LifecycleOwner, listName:String)

    fun deleteBookByTitle(title:String)
    fun sortByTitle() : List<BookVO>?
    fun sortByAuthor() : List<BookVO>?

    fun onTapView()
    fun onTapSort()
    fun onTapClose()
}