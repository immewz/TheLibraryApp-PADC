package com.mewz.thelibrary.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.mewz.thelibrary.data.vos.ShelfVO
import com.mewz.thelibrary.data.vos.overview.BookVO
import com.mewz.thelibrary.delegates.library.LibraryChipViewHolderDelegate
import com.mewz.thelibrary.mvp.views.ShelfDetailView

interface ShelfDetailPresenter: IBasePresenter, LibraryChipViewHolderDelegate {

    fun initView(view: ShelfDetailView)
    fun onUiReadyForShelfDetail(owner: LifecycleOwner, shelfId:Int)

    fun deleteShelf(shelfId: Int)
    fun updateShelf(shelf: ShelfVO)
    fun removeBook(title: String)

    fun sortByTitle() : List<BookVO>?
    fun sortByAuthor() : List<BookVO>?

    fun onTapBack()
    fun onTapOption()
    fun onTapView()
    fun onTapSort()
    fun onTapClearChip()
}