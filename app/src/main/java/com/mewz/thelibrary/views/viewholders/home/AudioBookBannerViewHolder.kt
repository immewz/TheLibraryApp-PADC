package com.mewz.thelibrary.views.viewholders.home

import android.view.View
import com.mewz.thelibrary.data.vos.overview.BookVO
import com.mewz.thelibrary.databinding.ViewHolderAudiobookBannerBinding
import com.mewz.thelibrary.delegates.home.BannerViewHolderDelegate

class AudioBookBannerViewHolder(itemView: View, private val delegate: BannerViewHolderDelegate) : IBookBannerViewHolder(itemView) {

    private var binding: ViewHolderAudiobookBannerBinding

    init {
        binding = ViewHolderAudiobookBannerBinding.bind(itemView)
    }

    override fun bindData(book: BookVO?) {
    }
}