package com.mewz.thelibrary.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mewz.thelibrary.R
import com.mewz.thelibrary.adapters.BookListAdapter
import com.mewz.thelibrary.data.vos.list.BookListVO
import com.mewz.thelibrary.databinding.ActivityBookListBinding
import com.mewz.thelibrary.mvp.presenters.BookListPresenter
import com.mewz.thelibrary.mvp.presenters.BookListPresenterImpl
import com.mewz.thelibrary.mvp.views.BookListView
import java.util.*

class BookListActivity : AppCompatActivity(), BookListView {

    private lateinit var binding: ActivityBookListBinding

    private lateinit var mBookListAdapter: BookListAdapter

    private lateinit var mPresenter: BookListPresenter

    companion object {
        private const val EXTRA_LIST_NAME = "EXTRA_LIST_NAME"
        private const val EXTRA_LIST_ID = "EXTRA_LIST_ID"
        private const val EXTRA_BOOK_TYPE = "EXTRA_BOOK_TYPE"

        fun newIntent(context: Context, listName:String, bookType:Int, listId: Int) : Intent {
            val intent = Intent(context,BookListActivity::class.java)
            intent.putExtra(EXTRA_LIST_NAME,listName)
            intent.putExtra(EXTRA_BOOK_TYPE,bookType)
            intent.putExtra(EXTRA_LIST_ID,listId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpPresenter()

        setUpRecyclerView()
        setUpListeners()

        val listName = intent?.getStringExtra(EXTRA_LIST_NAME) ?: ""
        binding.tvHeader.text = listName

        mPresenter.onUiReadyForBookList(this,
            listName.lowercase(Locale.ROOT).replace(' ','-',ignoreCase = true))
    }

    private fun setUpPresenter() {
        mPresenter =  ViewModelProvider(this)[BookListPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpListeners() {
        binding.btnBackBookList.setOnClickListener {
            mPresenter.onTapBack()
        }
    }

    private fun setUpRecyclerView() {
        mBookListAdapter = BookListAdapter(mPresenter)
        binding.rvCategoryBookList.adapter = mBookListAdapter
        binding.rvCategoryBookList.layoutManager =
            GridLayoutManager(this, 2)
    }

    override fun showBookList(bookList: List<BookListVO>) {
        mBookListAdapter.setData(bookList)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.deleteTheWholeBookList()
    }

    override fun navigateToBookDetailScreen(bookName: String, listId: Int) {
        startActivity(BookDetailActivity.newIntent(this,bookName,listId,"BookListActivity"))
    }

    override fun onTapBookOption() {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.bottom_sheet_option)
        dialog.show()
    }

    override fun navigateToBackScreen() {
        onBackPressed()
    }

    override fun showError(error: String) {
        Toast.makeText(this,error, Toast.LENGTH_SHORT).show()
    }
}