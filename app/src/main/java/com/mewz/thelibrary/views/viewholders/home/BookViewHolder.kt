package com.mewz.thelibrary.views.viewholders.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mewz.thelibrary.R
import com.mewz.thelibrary.data.vos.overview.BookVO
import com.mewz.thelibrary.databinding.ViewHolderBookBinding
import com.mewz.thelibrary.delegates.home.BookViewHolderDelegate

class BookViewHolder(itemView: View, private val delegate: BookViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var binding: ViewHolderBookBinding

    private var mBook:BookVO? = null
    private var mListId:Int = 0
    private var mListName:String = ""

    init {
        binding = ViewHolderBookBinding.bind(itemView)
        setUpListeners()
    }

    private fun setUpListeners() {
        itemView.setOnClickListener {
            mBook?.title?.let { bookName ->
                delegate.onTapBook(bookName,mListId)
            }
        }

        binding.btnOption.setOnClickListener {
            mBook?.let { book ->
                delegate.onTapBookOption(book,mListId,mListName)
            }
        }
    }

    fun bindData(book: BookVO, listId: Int, listName: String) {
        mBook = book
        mListId = listId
        mListName = listName

        Glide.with(itemView.context)
            .load(book.bookImage)
            .into(binding.ivBook)

        binding.tvBookTitle.text = book.title
        binding.tvAuthor.text = book.author
    }
}