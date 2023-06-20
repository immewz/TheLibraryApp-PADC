package com.mewz.thelibrary

import android.app.Application
import com.mewz.thelibrary.data.models.LibraryModelImpl

class LibraryApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        LibraryModelImpl.initDatabase(applicationContext)
    }
}