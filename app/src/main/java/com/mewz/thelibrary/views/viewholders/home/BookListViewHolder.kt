package com.mewz.thelibrary.views.viewholders.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mewz.thelibrary.data.vos.list.BookListVO
import com.mewz.thelibrary.databinding.ViewHolderBookListBinding
import com.mewz.thelibrary.delegates.home.BookViewHolderDelegate

class BookListViewHolder(itemView: View, private val delegate: BookViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderBookListBinding

    private var mBookName: String? = null
    private var mId: Int = 0

    init {
        binding = ViewHolderBookListBinding.bind(itemView)
        binding.flHeadphone.visibility = View.GONE
        binding.flSelected.visibility = View.GONE
        setUpListeners()
    }

    private fun setUpListeners() {
        itemView.setOnClickListener {
            mBookName?.let { bookName ->
                delegate.onTapBook(bookName,mId)
            }
        }

        binding.btnOption.setOnClickListener {
        }
    }

    fun bindData(bookList: BookListVO) {
        mBookName = bookList.bookDetails?.get(0)?.title
        mId = bookList.id

        binding.tvBookTitle.text = bookList.bookDetails?.get(0)?.title ?: ""
        binding.tvAuthor.text = bookList.bookDetails?.get(0)?.author ?: ""
    }
}