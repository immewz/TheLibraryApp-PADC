package com.mewz.thelibrary.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mewz.thelibrary.R
import com.mewz.thelibrary.activities.ShelfDetailActivity
import com.mewz.thelibrary.adapters.ShelvesListAdapter
import com.mewz.thelibrary.data.vos.ShelfVO
import com.mewz.thelibrary.databinding.FragmentLibraryBinding
import com.mewz.thelibrary.databinding.FragmentLibraryYourShelvesBinding
import com.mewz.thelibrary.delegates.library.ShelvesListViewHolderDelegate
import com.mewz.thelibrary.mvp.presenters.LibraryYourBookPresenter
import com.mewz.thelibrary.mvp.presenters.LibraryYourBookPresenterImpl
import com.mewz.thelibrary.mvp.presenters.LibraryYourShelvesPresenter
import com.mewz.thelibrary.mvp.presenters.LibraryYourShelvesPresenterImpl
import com.mewz.thelibrary.mvp.views.LibraryYourShelvesView

class LibraryYourShelvesFragment : Fragment(), LibraryYourShelvesView {

    private lateinit var binding: FragmentLibraryYourShelvesBinding

    private lateinit var mShelvesListAdapter: ShelvesListAdapter

    private lateinit var mPresenter: LibraryYourShelvesPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_library_your_shelves, container, false)

        binding = FragmentLibraryYourShelvesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()

        setUpRecyclerView()

        mPresenter.onUiReady(this)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[LibraryYourShelvesPresenterImpl::class.java]
        mPresenter.initView(this)
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun setUpRecyclerView() {
        mShelvesListAdapter = ShelvesListAdapter(mPresenter)
        binding.rvShelvesList.adapter = mShelvesListAdapter
        binding.rvShelvesList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

//        val linearLayoutManager = LinearLayoutManager(requireActivity())
//        val divider = DividerItemDecoration(context?.applicationContext, linearLayoutManager.orientation)
//        binding.rvShelvesList.addItemDecoration(divider)
    }

    override fun showShelfList(shelfList: List<ShelfVO>) {
        mShelvesListAdapter.setNewData(shelfList)
        Log.d("shelf","${shelfList}")
        showEmptyShelvesView()
    }

    private fun showEmptyShelvesView() {
        if (mShelvesListAdapter.itemCount > 0){
            binding.llEmptyShelves.visibility = View.GONE
        }else{
            binding.llEmptyShelves.visibility = View.VISIBLE
        }
    }

    override fun navigateToShelfDetailScreen(shelfId: Int) {
        startActivity(context?.let { ShelfDetailActivity.newIntent(it, shelfId) })
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }


}