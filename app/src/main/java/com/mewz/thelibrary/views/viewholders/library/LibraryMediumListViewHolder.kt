package com.mewz.thelibrary.views.viewholders.library

import android.view.View
import com.bumptech.glide.Glide
import com.mewz.thelibrary.data.vos.overview.BookVO
import com.mewz.thelibrary.databinding.ViewHolderBookBinding
import com.mewz.thelibrary.delegates.library.LibraryBookViewHolderDelegate

class LibraryMediumListViewHolder(itemView: View, private val delegate: LibraryBookViewHolderDelegate)
    : ILibraryBaseViewHolder(itemView) {

    private var binding: ViewHolderBookBinding
    private var mBook: BookVO? = null

    init {
        binding = ViewHolderBookBinding.bind(itemView)
        setUpListeners()
    }

    private fun setUpListeners() {
        itemView.setOnClickListener {
            delegate.onTapLibraryBook(mBook?.title ?: "",mBook?.listId ?: 0)
        }

        binding.btnOption.setOnClickListener {
            mBook?.let { book ->
                delegate.onTapLibraryBookOption(book)
            }
        }
    }

    override fun bindData(book: BookVO?) {
        mBook = book

        Glide.with(itemView.context)
            .load(book?.bookImage)
            .into(binding.ivBook)

        binding.tvBookTitle.text = book?.title ?: ""
        binding.tvAuthor.text = book?.author ?: ""
    }
}