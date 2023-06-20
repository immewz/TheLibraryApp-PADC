package com.mewz.thelibrary.views.viewholders.library

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mewz.thelibrary.R
import com.mewz.thelibrary.data.vos.ShelfVO
import com.mewz.thelibrary.databinding.ViewHolderShelvesListBinding
import com.mewz.thelibrary.delegates.library.ShelvesListViewHolderDelegate

class ShelvesListViewHolder(itemView: View, private val delegate: ShelvesListViewHolderDelegate)
    : RecyclerView.ViewHolder(itemView) {


    private var binding: ViewHolderShelvesListBinding

    private var mShelfId:Int = 0

    init {
        binding = ViewHolderShelvesListBinding.bind(itemView)
        setUpListeners()
    }

    private fun setUpListeners() {
        itemView.setOnClickListener {
            delegate.onTapShelvesBookList(mShelfId)
        }

        binding.btnSeeMore.setOnClickListener {
            delegate.onTapShelvesBookList(mShelfId)
        }
    }

    fun bindData(shelf: ShelfVO) {

        mShelfId = shelf.Id

        if(shelf.bookList?.size!! > 0) {
            Glide.with(itemView.context)
                .load(shelf.bookList?.get(shelf.bookList!!.lastIndex)?.bookImage)
                .into(binding.ivBookImage)
        }

        binding.tvBookTitle.text =shelf.shelfName

        var bookCount = "${shelf.bookCount} books"
        if (shelf.bookCount == 0){
            bookCount = "0 book"
        }

        binding.tvBookCount.text = bookCount

    }
}