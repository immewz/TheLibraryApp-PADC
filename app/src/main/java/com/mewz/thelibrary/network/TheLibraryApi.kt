package com.mewz.thelibrary.network

import com.mewz.thelibrary.network.responses.BookGoogleResponse
import com.mewz.thelibrary.network.responses.BookListResponse
import com.mewz.thelibrary.network.responses.BookOverviewResponse
import com.mewz.thelibrary.utils.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TheLibraryApi {

    @GET(API_GET_BOOK_OVERVIEW)
    fun getBookOverview(
        @Query(PARAM_API_KEY) api_key: String = API_KEY
    ): Observable<BookOverviewResponse>

    @GET(API_GET_BOOK_LIST)
    fun getBookList(
        @Query(PARAM_API_KEY) api_key: String = API_KEY,
        @Query(PARAM_LIST) list: String
    ): Observable<BookListResponse>

    @GET(API_GET_GOOGLE_BOOK_LIST)
    fun getGoogleBookList(
        @Query(PARAM_GOOGLE) q:String
    ) : Observable<BookGoogleResponse>

}