package com.mewz.thelibrary.mvp.presenters

import com.mewz.thelibrary.data.vos.overview.BookVO
import com.mewz.thelibrary.delegates.home.BannerViewHolderDelegate
import com.mewz.thelibrary.delegates.home.BookViewHolderDelegate
import com.mewz.thelibrary.mvp.views.HomeView

interface HomePresenter: IBasePresenter, BannerViewHolderDelegate, BookViewHolderDelegate {

    fun initView(view: HomeView)
    fun insertBookIntoLibrary(book: BookVO?)
    fun onTapGoToBookListScreen(listName:String, listId: Int)
}