package com.mewz.thelibrary.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mewz.thelibrary.R
import com.mewz.thelibrary.data.vos.ShelfVO
import com.mewz.thelibrary.delegates.library.AddToShelvesViewHolderDelegate
import com.mewz.thelibrary.views.viewholders.library.AddToShelvesViewHolder

class AddToShelvesListAdapter(
    private val delegate: AddToShelvesViewHolderDelegate
): RecyclerView.Adapter<AddToShelvesViewHolder>() {

    private var mShelfList: List<ShelfVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddToShelvesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_add_to_shelves, parent, false)
        return AddToShelvesViewHolder(view.rootView, delegate)
    }

    override fun getItemCount(): Int {
        return mShelfList.count()
    }

    override fun onBindViewHolder(holder: AddToShelvesViewHolder, position: Int) {
        holder.bindData(mShelfList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(shelfList: MutableList<ShelfVO>) {
        mShelfList = shelfList
        notifyDataSetChanged()
    }
}