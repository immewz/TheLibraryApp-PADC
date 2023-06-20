package com.mewz.thelibrary.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mewz.thelibrary.R
import com.mewz.thelibrary.delegates.library.LibraryChipViewHolderDelegate
import com.mewz.thelibrary.views.viewholders.library.LibraryChipViewHolder

class LibraryChipAdapter(
    private val delegate: LibraryChipViewHolderDelegate
): RecyclerView.Adapter<LibraryChipViewHolder>() {

    private var mChipList: MutableList<String> = mutableListOf()
    private var mClearChip: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryChipViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_library_chip, parent, false)
        return LibraryChipViewHolder(view.rootView, delegate)
    }

    override fun getItemCount(): Int {
        return mChipList.count()
    }

    override fun onBindViewHolder(holder: LibraryChipViewHolder, position: Int) {
         holder.bindData(mChipList[position],mClearChip)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(chipList: MutableList<String>) {
        if(chipList.isNotEmpty()) {
            mChipList = chipList.distinct() as MutableList<String>
            notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearChip(clear: Boolean) {
        mClearChip= clear
        notifyDataSetChanged()
    }
}