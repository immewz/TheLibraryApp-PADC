package com.mewz.thelibrary.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.mewz.thelibrary.data.vos.overview.BookVO
import com.mewz.thelibrary.mvp.views.BookDetailView

interface BookDetailPresenter: IBasePresenter {

    fun initView(view: BookDetailView)
    fun onUiReadyForBookDetail(owner: LifecycleOwner, listName:String, listId:Int, previousPlace:String)

    fun insertBookIntoLibrary(book: BookVO?)

    fun onTapBack()
    fun onTapAboutBook()
    fun onTapRatingAndReview()
}