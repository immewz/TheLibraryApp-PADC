package com.mewz.thelibrary.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.mewz.thelibrary.data.vos.ShelfVO
import com.mewz.thelibrary.delegates.library.AddToShelvesViewHolderDelegate
import com.mewz.thelibrary.mvp.views.AddToShelvesView

interface AddToShelvesPresenter: IBasePresenter, AddToShelvesViewHolderDelegate {

    fun initView(view: AddToShelvesView)
    fun onUiReadyForAddToShelves(owner: LifecycleOwner, title:String)

    fun onTapClose()

    fun updateShelf(shelf: ShelfVO)
}