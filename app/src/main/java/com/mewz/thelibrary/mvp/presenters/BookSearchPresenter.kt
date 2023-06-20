package com.mewz.thelibrary.mvp.presenters

import com.mewz.thelibrary.mvp.views.BookSearchView

interface BookSearchPresenter: IBasePresenter {
    fun initView(view: BookSearchView)

}