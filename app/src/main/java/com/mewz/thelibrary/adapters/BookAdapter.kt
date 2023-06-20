package com.mewz.thelibrary.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mewz.thelibrary.R
import com.mewz.thelibrary.data.vos.overview.BookCategoryVO
import com.mewz.thelibrary.delegates.home.BookViewHolderDelegate
import com.mewz.thelibrary.views.viewholders.home.BookViewHolder

class BookAdapter(
    private val delegate: BookViewHolderDelegate
): RecyclerView.Adapter<BookViewHolder>() {

    private var mBookCategory: BookCategoryVO? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_book, parent, false)
        return BookViewHolder(view.rootView, delegate)
    }

    override fun getItemCount(): Int {
        return mBookCategory?.books?.size ?: 0
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        mBookCategory?.books?.get(position)?.let {
            holder.bindData(it,mBookCategory?.listId ?: 0, mBookCategory?.listName ?: "")
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(category: BookCategoryVO) {
        mBookCategory = category
        notifyDataSetChanged()
    }
}