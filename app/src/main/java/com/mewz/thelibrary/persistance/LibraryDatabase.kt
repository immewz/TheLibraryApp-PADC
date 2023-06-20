package com.mewz.thelibrary.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mewz.thelibrary.data.vos.SearchBookVO
import com.mewz.thelibrary.data.vos.ShelfVO
import com.mewz.thelibrary.data.vos.google.GoogleBookVO
import com.mewz.thelibrary.data.vos.list.BookListVO
import com.mewz.thelibrary.data.vos.overview.BookCategoryVO
import com.mewz.thelibrary.data.vos.overview.BookVO
import com.mewz.thelibrary.persistance.daos.LibraryDao

@Database([BookCategoryVO::class, BookListVO::class, ShelfVO::class, BookVO::class,
GoogleBookVO::class, SearchBookVO::class], version = 1, exportSchema = false)
abstract class LibraryDatabase: RoomDatabase() {

    abstract fun libraryDao(): LibraryDao

    companion object {
        private var libraryDatabase: LibraryDatabase? = null
        private const val DB_NAME = "TheLibraryDatabase"

        fun getDbInstance(context: Context) : LibraryDatabase? {
            when(libraryDatabase) {
                null -> {
                    libraryDatabase = Room.databaseBuilder(context, LibraryDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return libraryDatabase
        }
    }
}