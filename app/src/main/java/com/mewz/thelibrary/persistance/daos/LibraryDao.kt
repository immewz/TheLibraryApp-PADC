package com.mewz.thelibrary.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mewz.thelibrary.data.vos.SearchBookVO
import com.mewz.thelibrary.data.vos.ShelfVO
import com.mewz.thelibrary.data.vos.list.BookListVO
import com.mewz.thelibrary.data.vos.overview.BookCategoryVO
import com.mewz.thelibrary.data.vos.overview.BookVO

@Dao
interface LibraryDao {

    // Category
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategories(categoryList: List<BookCategoryVO>)

    @Query("SELECT * FROM book_category_table")
    fun getCategories(): LiveData<List<BookCategoryVO>>

    @Query("SELECT * FROM book_category_table WHERE listId = :listId")
    fun getCategoryByListId(listId:Int): LiveData<BookCategoryVO?>

    // Book List
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookList(bookList:List<BookListVO>)

    @Query("SELECT * FROM book_list_table")
    fun getBookList(): LiveData<List<BookListVO>>

    @Query("SELECT * FROM book_list_table WHERE id = :bookListId")
    fun getBookFromBookListById(bookListId:Int): LiveData<BookListVO>

    @Query("DELETE FROM book_list_table")
    fun deleteTheWholeBookList()

    // Shelf
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShelf(shelf: ShelfVO)

    @Query("SELECT * FROM shelf_table")
    fun getShelfList(): LiveData<List<ShelfVO>>

    @Query("SELECT * FROM shelf_table WHERE id = :shelfId")
    fun getShelfById(shelfId:Int): LiveData<ShelfVO>

    @Query("DELETE FROM shelf_table WHERE id = :shelfId")
    fun deleteShelfById(shelfId:Int)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateShelf(shelf:ShelfVO)

    // Library
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookIntoLibrary(book: BookVO)

    @Query("SELECT * FROM library_table")
    fun getAllBooksFromLibrary(): LiveData<List<BookVO>>

    @Query("DELETE FROM library_table WHERE title = :title")
    fun deleteBookByTitle(title:String)

    @Query("SELECT * FROM library_table WHERE title = :title")
    fun getBookByTitle(title:String): LiveData<BookVO?>

    // Search
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGoogleBookList(bookList:List<BookVO>)

    @Query("SELECT * FROM library_table")
    fun getGoogleBookList(): LiveData<List<BookVO>>

    @Query("SELECT * FROM library_table WHERE list_name = :listName")
    fun getBookListByListName(listName:String): LiveData<List<BookVO>>

    @Query("SELECT * FROM search_table")
    fun getSearchBookList(): LiveData<List<SearchBookVO>>

    @Query("SELECT * FROM search_table WHERE title = :title")
    fun getBookFromSearchTable(title:String): LiveData<SearchBookVO?>

    @Query("DELETE FROM search_table")
    fun deleteSearchBookList()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookIntoSearchTable(book:SearchBookVO)
}