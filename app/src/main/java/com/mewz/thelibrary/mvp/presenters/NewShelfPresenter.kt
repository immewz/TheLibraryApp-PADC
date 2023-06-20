package com.mewz.thelibrary.mvp.presenters

import com.mewz.thelibrary.data.vos.ShelfVO
import com.mewz.thelibrary.mvp.views.NewShelfView

interface NewShelfPresenter: IBasePresenter {

    fun initView(view: NewShelfView)
    fun insertShelf(shelf: ShelfVO)

    fun onTapBack()
}