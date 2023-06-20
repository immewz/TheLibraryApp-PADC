package com.mewz.thelibrary.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mewz.thelibrary.R
import com.mewz.thelibrary.adapters.AddToShelvesListAdapter
import com.mewz.thelibrary.data.vos.ShelfVO
import com.mewz.thelibrary.data.vos.overview.BookVO
import com.mewz.thelibrary.databinding.ActivityAddToShelvesBinding
import com.mewz.thelibrary.mvp.presenters.AddToShelvesPresenter
import com.mewz.thelibrary.mvp.presenters.AddToShelvesPresenterImpl
import com.mewz.thelibrary.mvp.views.AddToShelvesView

class AddToShelvesActivity : AppCompatActivity(), AddToShelvesView {

    private lateinit var binding: ActivityAddToShelvesBinding

    private lateinit var mAddToShelvesListAdapter: AddToShelvesListAdapter

    private lateinit var mPresenter: AddToShelvesPresenter

    private var mShelfList:MutableList<ShelfVO> = mutableListOf()
    private var mBookTitle:String = ""
    private var mBook: BookVO? = null
    private var mIsChecked = false

    companion object {
        private const val EXTRA_BOOK_TITLE = "EXTRA_BOOK_TITLE"
        fun newIntent(context: Context, title:String) : Intent {
            val intent = Intent(context,AddToShelvesActivity::class.java)
            intent.putExtra(EXTRA_BOOK_TITLE,title)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddToShelvesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpPresenter()

        setUpRecyclerView()
        setUpListeners()

        mBookTitle = intent?.getStringExtra(EXTRA_BOOK_TITLE) ?: ""

        mPresenter.onUiReadyForAddToShelves(this, mBookTitle)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[AddToShelvesPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpRecyclerView() {
        mAddToShelvesListAdapter = AddToShelvesListAdapter(mPresenter)
        binding.rvTapShelvesList.adapter = mAddToShelvesListAdapter
        binding.rvTapShelvesList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val linearLayoutManager = LinearLayoutManager(this)
        val divider = DividerItemDecoration(this, linearLayoutManager.orientation)
        binding.rvTapShelvesList.addItemDecoration(divider)
        mAddToShelvesListAdapter.notifyDataSetChanged()
    }

    private fun setUpListeners() {
        binding.btnClose.setOnClickListener {
            mPresenter.onTapClose()
        }
    }

    override fun showShelfList(shelfList: List<ShelfVO>) {
        mShelfList = shelfList as MutableList<ShelfVO>

        for(shelf in mShelfList) {
            for(book in shelf.bookList!!) {
                shelf.isChecked = mBookTitle == book.title
            }
        }
        mAddToShelvesListAdapter.setNewData(mShelfList)
    }

    override fun showBook(book: BookVO?) {
        mBook = book
    }

    override fun closeAddToShelvesActivity() {
        finish()
    }

    override fun onClickCheckBox(shelfId: Int, checked: Boolean) {
        for(shelf in mShelfList) {
            if(shelfId == shelf.Id) {
                if(checked) {
                    shelf.isChecked = true
                    mBook?.let { shelf.bookList?.add(it) }
                    shelf.bookCount = shelf.bookList?.size
                    val shelfVO = ShelfVO(shelfId,shelf.shelfName,shelf.bookCount,shelf.bookList,shelf.isChecked)
                    mPresenter.updateShelf(shelfVO)
                    break
                }else {
                    shelf.isChecked = false
                    mBook?.let { shelf.bookList?.remove(it) }
                    shelf.bookCount = shelf.bookList?.size
                    val shelfVO = ShelfVO(shelf.Id,shelf.shelfName,shelf.bookCount,shelf.bookList,shelf.isChecked)
                    mPresenter.updateShelf(shelfVO)
                    break
                }

            }
        }
    }

    override fun showError(error: String) {
        Toast.makeText(this,error, Toast.LENGTH_SHORT).show()
    }
}