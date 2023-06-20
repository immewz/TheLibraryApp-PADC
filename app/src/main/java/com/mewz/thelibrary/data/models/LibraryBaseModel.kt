package com.mewz.thelibrary.data.models

import android.content.Context
import com.mewz.thelibrary.network.TheLibraryApi
import com.mewz.thelibrary.persistance.LibraryDatabase
import com.mewz.thelibrary.utils.BASE_URL
import com.mewz.thelibrary.utils.GOOGLE_BASE_URL
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class LibraryBaseModel {

    protected var mLibraryApi: TheLibraryApi
    protected var mLibraryApiTwo: TheLibraryApi
    protected var mLibraryDatabase: LibraryDatabase? = null

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofitOne = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        val retrofitTwo = Retrofit.Builder()
            .baseUrl(GOOGLE_BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        mLibraryApi = retrofitOne.create(TheLibraryApi::class.java)
        mLibraryApiTwo = retrofitTwo.create(TheLibraryApi::class.java)
    }

    fun initDatabase(context: Context) {
        mLibraryDatabase = LibraryDatabase.getDbInstance(context)
    }
}