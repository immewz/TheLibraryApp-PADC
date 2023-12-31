package com.mewz.thelibrary.data.models

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import com.mewz.thelibrary.data.vos.SearchBookVO
import com.mewz.thelibrary.data.vos.ShelfVO
import com.mewz.thelibrary.data.vos.list.BookListVO
import com.mewz.thelibrary.data.vos.overview.BookCategoryVO
import com.mewz.thelibrary.data.vos.overview.BookVO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object LibraryModelImpl: LibraryBaseModel(), LibraryModel {

    @SuppressLint("CheckResult")
    override fun getBookOverview(onFailure: (String) -> Unit): LiveData<List<BookCategoryVO>>? {
        mLibraryApi.getBookOverview()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mLibraryDatabase?.libraryDao()?.insertCategories(it.results?.lists ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })
        return mLibraryDatabase?.libraryDao()?.getCategories()
    }

    @SuppressLint("CheckResult")
    override fun getBookList(
        listName: String,
        onFailure: (String) -> Unit
    ): LiveData<List<BookListVO>>? {
        mLibraryApi.getBookList(list = listName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mLibraryDatabase?.libraryDao()?.insertBookList(it.results ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })
        return mLibraryDatabase?.libraryDao()?.getBookList()
    }


    override fun getCategoryByListId(listId: Int): LiveData<BookCategoryVO?>? =
        mLibraryDatabase?.libraryDao()?.getCategoryByListId(listId)

    override fun getBookFromBookListById(bookListId: Int): LiveData<BookListVO>? =
        mLibraryDatabase?.libraryDao()?.getBookFromBookListById(bookListId)

    override fun deleteTheWholeBookList() {
        mLibraryDatabase?.libraryDao()?.deleteTheWholeBookList()
    }


    override fun insertShelf(shelf: ShelfVO) {
        mLibraryDatabase?.libraryDao()?.insertShelf(shelf)
    }

    override fun getShelfList(): LiveData<List<ShelfVO>>? {
        return mLibraryDatabase?.libraryDao()?.getShelfList()
    }

    override fun getShelfById(shelfId: Int): LiveData<ShelfVO>? {
        return mLibraryDatabase?.libraryDao()?.getShelfById(shelfId)
    }

    override fun deleteShelf(shelfId: Int) {
        mLibraryDatabase?.libraryDao()?.deleteShelfById(shelfId)
    }

    override fun updateShelf(shelf: ShelfVO) {
        mLibraryDatabase?.libraryDao()?.updateShelf(shelf)
    }


    override fun insertBookIntoLibrary(bookVO: BookVO?) {
        if (bookVO != null) {
            mLibraryDatabase?.libraryDao()?.insertBookIntoLibrary(bookVO)
        }
    }

    override fun getAllBooksFromLibrary(): LiveData<List<BookVO>>? {
        return mLibraryDatabase?.libraryDao()?.getAllBooksFromLibrary()
    }

    override fun deleteBookByTitle(title: String) {
        mLibraryDatabase?.libraryDao()?.deleteBookByTitle(title)
    }

    override fun getBookByTitle(title: String): LiveData<BookVO?>? {
        return mLibraryDatabase?.libraryDao()?.getBookByTitle(title)
    }


    override fun searchBookFromGoogle(query: String): Observable<List<BookVO>> {
        val googleBookList = mLibraryApiTwo.getGoogleBookList(query).map {
            it.items ?: listOf()
        }

        return googleBookList.map { googleBooks ->
            googleBooks.map { googleBook ->
                BookVO(
                    title = googleBook.volumeInfo?.title ?: "",
                    author = googleBook.volumeInfo?.authors?.get(0) ?: "",
                    description = googleBook.volumeInfo?.description ?: "",
                    ageGroup = null,
                    amazonProductUrl = null,
                    articleChapterLink = null,
                    bookImage = googleBook.volumeInfo?.imageLinks?.thumbnail,
                    bookImageHeight = null,
                    bookImageWidth = null,
                    bookReviewLink = null,
                    contributor = null,
                    contributorNote = null,
                    createdDate = null,
                    firstChapterLink = null,
                    primaryIsbn10 = null,
                    primaryIsbn13 = null,
                    publisher = null,
                    rank = null,
                    rankLastWeek = null,
                    sundayReviewLink = null,
                    updatedDate = null,
                    weeksOnList = null,
                    listId = null,
                    listName = null,
                    bookUri = googleBook.volumeInfo?.imageLinks?.smallThumbnail,
                    buyLinks = null,
                    price = null
                )
            }
        }.onErrorResumeNext{
            Observable.just(listOf())
        }
            .subscribeOn(Schedulers.io())

    }

    override fun getBookListByListName(listName: String): LiveData<List<BookVO>>? {
        return mLibraryDatabase?.libraryDao()?.getBookListByListName(listName)
    }

    override fun getSearchBookList(): LiveData<List<SearchBookVO>>? {
        return mLibraryDatabase?.libraryDao()?.getSearchBookList()
    }

    override fun getBookFromSearchTable(title: String): LiveData<SearchBookVO?>? {
        return mLibraryDatabase?.libraryDao()?.getBookFromSearchTable(title)
    }

    override fun deleteSearchBookList() {
        mLibraryDatabase?.libraryDao()?.deleteSearchBookList()
    }

    override fun insertBookIntoSearchTable(book: SearchBookVO?) {
        if (book != null) {
            mLibraryDatabase?.libraryDao()?.insertBookIntoSearchTable(book)
        }
    }
}