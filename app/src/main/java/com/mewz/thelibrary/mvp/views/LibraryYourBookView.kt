package com.mewz.thelibrary.mvp.views

import com.mewz.thelibrary.data.vos.overview.BookVO

interface LibraryYourBookView: IBaseView {

    fun showBooksInLibrary(bookList: List<BookVO>?)
    fun showBookListByListName(bookList: List<BookVO>?)

    fun showBottomSheetDialogForView()
    fun showBottomSheetDialogForSorting()

    fun onTapChip(listName: String)
    fun onTapCloseAllChip()
}