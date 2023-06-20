package com.mewz.thelibrary.fragments

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mewz.thelibrary.R
import com.mewz.thelibrary.activities.AddToShelvesActivity
import com.mewz.thelibrary.activities.BookDetailActivity
import com.mewz.thelibrary.activities.BookListActivity
import com.mewz.thelibrary.adapters.BookBannerAdapter
import com.mewz.thelibrary.data.vos.overview.BookCategoryVO
import com.mewz.thelibrary.data.vos.overview.BookVO
import com.mewz.thelibrary.databinding.FragmentHomeBinding
import com.mewz.thelibrary.delegates.home.BannerViewHolderDelegate
import com.mewz.thelibrary.delegates.home.BookViewHolderDelegate
import com.mewz.thelibrary.mvp.presenters.HomePresenter
import com.mewz.thelibrary.mvp.presenters.HomePresenterImpl
import com.mewz.thelibrary.mvp.views.HomeView
import com.mewz.thelibrary.utils.bookTab
import com.mewz.thelibrary.views.viewpods.BookListViewPod
import java.util.function.LongFunction

class HomeFragment : Fragment(), HomeView {

    private lateinit var binding: FragmentHomeBinding

    private var mBookBannerAdapter: BookBannerAdapter? = null

    private lateinit var mFirstCategoryViewPod: BookListViewPod
    private lateinit var mSecondCategoryViewPod: BookListViewPod
    private lateinit var mThirdCategoryViewPod: BookListViewPod

    private lateinit var mPresenter: HomePresenter

    private var mFirstListId = 0
    private var mSecondListId = 0
    private var mThirdListId = 0

    private var mBookList: List<BookVO> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_home, container, false)

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()

        setUpBannerViewPager(0)
        setUpBookTabLayout()
        setUpViewPod()
        setUpListeners()
        setUpHideOrShowBanner()

        mPresenter.onUiReady(this)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(requireActivity())[HomePresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpHideOrShowBanner() {
        mBookBannerAdapter?.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver(){
            override fun onChanged() {
                val itemCount = mBookBannerAdapter?.itemCount
                if(itemCount == 0) {
                    binding.viewPagerBanner.visibility = View.GONE
                } else {
                    binding.viewPagerBanner.visibility = View.VISIBLE
                }
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                val updatedItemCount = mBookBannerAdapter?.itemCount
                if(updatedItemCount == 0) {
                    binding.viewPagerBanner.visibility = View.GONE
                } else {
                    binding.viewPagerBanner.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setUpListeners() {
        binding.btnFirstSeeMore.setOnClickListener {
            mPresenter.onTapGoToBookListScreen(binding.tvFirstCategory.text.toString(), mFirstListId)
        }

        binding.btnSecondSeeMore.setOnClickListener {
            mPresenter.onTapGoToBookListScreen(binding.tvSecondCategory.text.toString(), mSecondListId)
        }

        binding.btnThirdSeeMore.setOnClickListener {
            mPresenter.onTapGoToBookListScreen(binding.tvThirdCategory.text.toString(), mThirdListId)
        }
    }

    private fun setUpViewPod() {
        mFirstCategoryViewPod = binding.vpFirstBookList.root
        mFirstCategoryViewPod.setUpBookViewPod(mPresenter)

        mSecondCategoryViewPod = binding.vpSecondBookList.root
        mSecondCategoryViewPod.setUpBookViewPod(mPresenter)

        mThirdCategoryViewPod = binding.vpThirdBookList.root
        mThirdCategoryViewPod.setUpBookViewPod(mPresenter)
    }

    private fun setUpBookTabLayout() {
        bookTab.forEach{
            binding.tlBook.newTab().apply {
                text = it
                binding.tlBook.addTab(this)
            }
        }

//        binding.tlBook.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//            }
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//            }
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//            }
//        })

    }

    private fun setUpBannerViewPager(bookType: Int) {
        setUpBannerViewPagerPadding()
        setUpBannerRecyclerView(bookType)

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((20 * Resources.getSystem().displayMetrics.density).toInt()))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - kotlin.math.abs(position)
            page.scaleY = (0.80f + r * 0.20f)
        }
        binding.viewPagerBanner.setPageTransformer(compositePageTransformer)
    }

    private fun setUpBannerViewPagerPadding() {
        binding.viewPagerBanner.apply {
            clipChildren = false  // No clipping the left and right items
            clipToPadding = false  // Show the viewpager in full width without clipping the padding
            offscreenPageLimit = 3  // Render the left and right items
            (getChildAt(0) as RecyclerView).overScrollMode =
                RecyclerView.OVER_SCROLL_ALWAYS // Remove the scroll effect
        }
    }

    private fun setUpBannerRecyclerView(bookType: Int) {
        mBookBannerAdapter = BookBannerAdapter(bookType, mPresenter)
        binding.viewPagerBanner.adapter = mBookBannerAdapter
    }

    override fun showBooksForBanner(bookList: List<BookVO>?) {
        mBookList = bookList ?: listOf()
        mBookBannerAdapter?.setNewData(bookList)
    }

    override fun showFirstCategory(category: List<BookCategoryVO>?) {
        if (category?.size != 0){
            binding.tvFirstCategory.text = category?.get(0)?.listName ?: ""
            mFirstListId = category?.get(0)?.listId ?: 0
            category?.get(0)?.let {
                mFirstCategoryViewPod.setNewData(it)
            }
        }
    }

    override fun showSecondCategory(category: List<BookCategoryVO>?) {
        if (category?.size != 0){
            binding.tvSecondCategory.text = category?.get(1)?.listName ?: ""
            mSecondListId = category?.get(1)?.listId ?: 0
            category?.get(1)?.let {
                mSecondCategoryViewPod.setNewData(it)
            }
        }
    }

    override fun showThirdCategory(category: List<BookCategoryVO>?) {
        if (category?.size != 0){
            binding.tvThirdCategory.text = category?.get(2)?.listName ?: ""
            mThirdListId = category?.get(2)?.listId ?: 0
            category?.get(2)?.let {
                mThirdCategoryViewPod.setNewData(it)
            }
        }
    }

    override fun navigateToBookDetailScreen(bookName: String, listId: Int) {
        startActivity(context?.let { BookDetailActivity.newIntent(it, bookName, listId, "HomeFragment") })
    }

    override fun navigateToBookListScreen(listName: String, listId: Int) {
        startActivity(context?.let { BookListActivity.newIntent(it, listName, binding.tlBook.selectedTabPosition,listId) })
    }

    override fun onTapBookOption(book: BookVO?, listId: Int, listName: String) {
        val dialog = BottomSheetDialog(requireActivity())
        dialog.setContentView(R.layout.bottom_sheet_option)
        dialog.show()

        dialog.findViewById<AppCompatImageView>(R.id.ivBookImage)?.let {
            Glide.with(requireActivity())
                .load(book?.bookImage)
                .into(it)
        }

        dialog.findViewById<AppCompatTextView>(R.id.tvBookTitle)?.text = book?.title
        dialog.findViewById<AppCompatTextView>(R.id.tvAuthor)?.text = book?.author

        dialog.findViewById<LinearLayout>(R.id.btnBottomSheetAddToLibrary)?.setOnClickListener {
            book?.listId = listId
            book?.listName = listName
            mPresenter.insertBookIntoLibrary(book)
            dialog.dismiss()
        }

        dialog.findViewById<LinearLayout>(R.id.btnBottomSheetDeleteLibraryBook)?.visibility = View.GONE
        dialog.findViewById<LinearLayout>(R.id.btnBottomSheetDeleteShelvesBook)?.visibility = View.GONE

        dialog.findViewById<LinearLayout>(R.id.btnBottomSheetAddToShelve)?.setOnClickListener {
            startActivity(context?.let { AddToShelvesActivity.newIntent(it, book?.title ?: "") })
        }
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

}