package com.mewz.thelibrary.mvp.views

import com.mewz.thelibrary.data.vos.ShelfVO

interface LibraryYourShelvesView: IBaseView {
    fun showShelfList(shelfList: List<ShelfVO>)
    fun navigateToShelfDetailScreen(shelfId: Int)
}