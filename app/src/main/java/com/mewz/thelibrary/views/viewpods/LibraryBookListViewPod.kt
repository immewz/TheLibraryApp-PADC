package com.mewz.thelibrary.views.viewpods

import android.content.Context
import android.util.AttributeSet
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mewz.thelibrary.adapters.LibraryBookListAdapter
import com.mewz.thelibrary.adapters.LibraryChipAdapter
import com.mewz.thelibrary.data.vos.overview.BookVO
import com.mewz.thelibrary.databinding.ViewPodLibraryBookListBinding
import com.mewz.thelibrary.delegates.library.LibraryBookViewHolderDelegate
import com.mewz.thelibrary.delegates.library.LibraryChipViewHolderDelegate

class LibraryBookListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : NestedScrollView(context, attrs) {

    private lateinit var binding: ViewPodLibraryBookListBinding

    private lateinit var mLibraryBookListAdapter: LibraryBookListAdapter
    private lateinit var mLibraryChipAdapter: LibraryChipAdapter

    private lateinit var mDelegate: LibraryBookViewHolderDelegate
    private lateinit var mDelegateChip: LibraryChipViewHolderDelegate

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding = ViewPodLibraryBookListBinding.bind(this)
    }

    fun setDelegate(viewList: Int,delegate: LibraryBookViewHolderDelegate) {
        mDelegate = delegate
        setUpRecyclerViewForBooks(viewList)
        when (viewList) {
            1 -> {
                binding.rvLibraryBookListView.layoutManager = LinearLayoutManager(context)
            }
            2 -> {
                binding.rvLibraryBookListView.layoutManager = GridLayoutManager(context, viewList)
            }
            else -> {
                binding.rvLibraryBookListView.layoutManager = GridLayoutManager(context, viewList)
            }
        }
    }

    private fun setUpRecyclerViewForBooks(viewList: Int) {
        mLibraryBookListAdapter = LibraryBookListAdapter(viewList, mDelegate)
        binding.rvLibraryBookListView.adapter = mLibraryBookListAdapter
    }

    fun setDelegateForChip(delegate: LibraryChipViewHolderDelegate) {
        mDelegateChip = delegate
        setUpRecyclerViewForChipList()
    }


    private fun setUpRecyclerViewForChipList() {
        mLibraryChipAdapter = LibraryChipAdapter(mDelegateChip)
        binding.rvChipList.adapter = mLibraryChipAdapter
        binding.rvChipList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
    }

    fun setNewData(bookList: List<BookVO>?) {
        mLibraryBookListAdapter.setData(bookList)
    }

    fun setChipData(chipList: MutableList<String>) {
        mLibraryChipAdapter.setData(chipList)
    }

    fun clearChip(isClear: Boolean) {
        mLibraryChipAdapter.clearChip(isClear)
    }
}