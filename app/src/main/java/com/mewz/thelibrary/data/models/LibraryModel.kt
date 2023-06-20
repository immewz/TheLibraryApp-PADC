package com.mewz.thelibrary.data.models

import androidx.lifecycle.LiveData
import com.mewz.thelibrary.data.vos.SearchBookVO
import com.mewz.thelibrary.data.vos.ShelfVO
import com.mewz.thelibrary.data.vos.list.BookListVO
import com.mewz.thelibrary.data.vos.overview.BookCategoryVO
import com.mewz.thelibrary.data.vos.overview.BookVO
import io.reactivex.rxjava3.core.Observable

interface LibraryModel {

    fun getBookOverview(
        onFailure: (String) -> Unit
    ) : LiveData<List<BookCategoryVO>>?

    fun getBookList(
        listName:String,
        onFailure: (String) -> Unit
    ) : LiveData<List<BookListVO>>?


    fun getCategoryByListId(listId:Int) : LiveData<BookCategoryVO?>?

    fun getBookFromBookListById(bookListId:Int) : LiveData<BookListVO>?

    fun deleteTheWholeBookList()


    fun insertShelf(shelf: ShelfVO)

    fun getShelfList(): LiveData<List<ShelfVO>>?

    fun getShelfById(shelfId:Int): LiveData<ShelfVO>?

    fun deleteShelf(shelfId:Int)

    fun updateShelf(shelf: ShelfVO)


    fun insertBookIntoLibrary(bookVO: BookVO?)

    fun getAllBooksFromLibrary() : LiveData<List<BookVO>>?

    fun deleteBookByTitle(title:String)

    fun getBookByTitle(title:String) : LiveData<BookVO?>?


    fun searchBookFromGoogle(
        query:String
    ) : Observable<List<BookVO>>

    fun getBookListByListName(listName:String): LiveData<List<BookVO>>?

    fun getSearchBookList(): LiveData<List<SearchBookVO>>?

    fun getBookFromSearchTable(title:String): LiveData<SearchBookVO?>?

    fun deleteSearchBookList()

    fun insertBookIntoSearchTable(book:SearchBookVO?)
}