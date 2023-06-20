package com.mewz.thelibrary.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mewz.thelibrary.R
import com.mewz.thelibrary.data.vos.overview.BookVO
import com.mewz.thelibrary.delegates.library.LibraryBookViewHolderDelegate
import com.mewz.thelibrary.views.viewholders.library.LibrarySmallListViewHolder

class BookSearchAdapter(
    private val delegate: LibraryBookViewHolderDelegate
): RecyclerView.Adapter<LibrarySmallListViewHolder>() {

    private var mBookList:List<BookVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibrarySmallListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_library_book_small_list, parent, false)
        return LibrarySmallListViewHolder(view.rootView, delegate)
    }

    override fun getItemCount(): Int {
        return mBookList.count()
    }

    override fun onBindViewHolder(holder: LibrarySmallListViewHolder, position: Int) {
        if (mBookList.isNotEmpty()){
            holder.bindData(mBookList[position])
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(bookList: List<BookVO>?) {
        mBookList = bookList ?: listOf()
        notifyDataSetChanged()
    }
}