package com.mewz.thelibrary.views.viewholders.library

import android.view.View
import com.bumptech.glide.Glide
import com.mewz.thelibrary.data.vos.overview.BookVO
import com.mewz.thelibrary.databinding.ViewHolderLibraryBookSmallListBinding
import com.mewz.thelibrary.delegates.library.LibraryBookViewHolderDelegate

class LibrarySmallListViewHolder(itemView: View, private val delegate: LibraryBookViewHolderDelegate)
    : ILibraryBaseViewHolder(itemView) {

    private var binding: ViewHolderLibraryBookSmallListBinding
    private var mBook: BookVO? = null

    init {
        binding = ViewHolderLibraryBookSmallListBinding.bind(itemView)
        setUpListeners()
    }

    private fun setUpListeners() {
        itemView.setOnClickListener {
            delegate.onTapLibraryBook(mBook?.title ?: "", mBook?.listId ?: 0)
        }

        binding.btnOption.setOnClickListener {
            mBook?.let { book ->
                delegate.onTapLibraryBookOption(book)
            }
        }
    }

    override fun bindData(book: BookVO?) {
        mBook = book

        book?.bookImage = book?.bookImage?.replace("http://", "https://")

        Glide.with(itemView.context)
            .load(book?.bookImage)
            .into(binding.ivBookImage)

        binding.tvBookTitle.text = book?.title ?: ""
        binding.tvAuthor.text = book?.author ?: ""
    }
}