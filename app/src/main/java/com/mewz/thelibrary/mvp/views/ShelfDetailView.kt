package com.mewz.thelibrary.mvp.views

import com.mewz.thelibrary.data.vos.ShelfVO

interface ShelfDetailView: IBaseView {

    fun showBottomSheetDialogForShelfTitle()
    fun showBottomSheetDialogForView()
    fun showBottomSheetDialogForSorting()

    fun showShelfDetail(shelfVO: ShelfVO?)

    fun navigateToBack()
    fun onTapChip(listName: String)
    fun onTapClearChip()


}