package com.mewz.thelibrary.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding4.widget.textChanges
import com.mewz.thelibrary.R
import com.mewz.thelibrary.adapters.BookSearchAdapter
import com.mewz.thelibrary.data.models.LibraryModel
import com.mewz.thelibrary.data.models.LibraryModelImpl
import com.mewz.thelibrary.data.vos.SearchBookVO
import com.mewz.thelibrary.data.vos.overview.BookVO
import com.mewz.thelibrary.databinding.ActivityBookSearchBinding
import com.mewz.thelibrary.delegates.library.LibraryBookViewHolderDelegate
import com.mewz.thelibrary.utils.bookTab
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class BookSearchActivity : AppCompatActivity(), LibraryBookViewHolderDelegate{

    private lateinit var binding: ActivityBookSearchBinding

    private lateinit var mBookSearchAdapter: BookSearchAdapter

    private val mLibraryModel: LibraryModel = LibraryModelImpl

    private var mBookList:List<BookVO> = listOf()

    companion object{
        fun newIntent(context: Context): Intent {
            return Intent(context, BookSearchActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpTabLayout()
        setUpRecyclerView()
        setUpListeners()

        binding.tlBook.visibility = View.GONE
        binding.tvGoogle.visibility = View.GONE

    }

    @SuppressLint("CheckResult")
    private fun setUpListeners() {
        binding.etSearch.textChanges()
            .debounce(500L, TimeUnit.MILLISECONDS)
            .flatMap {
                mLibraryModel.searchBookFromGoogle(it.toString())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ bookList ->
                mBookList = bookList
                mBookSearchAdapter.setData(bookList)
                mLibraryModel.deleteSearchBookList()
                for(book in bookList) {
                    val title = book.title
                    val author = book.author
                    val description = book.description
                    val image = book.bookImage
                    val searchBook = SearchBookVO(title,author,description,image)
                    mLibraryModel.insertBookIntoSearchTable(searchBook)
                    binding.tlBook.visibility = View.VISIBLE
                    binding.tvGoogle.visibility = View.VISIBLE
                }
            },{
                Toast.makeText(this,it.localizedMessage, Toast.LENGTH_SHORT).show()
            })
    }

    private fun setUpRecyclerView() {
        mBookSearchAdapter = BookSearchAdapter(this)
        binding.rvSearchBookList.adapter = mBookSearchAdapter
        binding.rvSearchBookList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setUpTabLayout() {
        bookTab.forEach{
            binding.tlBook.newTab().apply {
                text = it
                binding.tlBook.addTab(this)
            }
        }
    }

    override fun onTapLibraryBook(bookName: String, listId: Int) {
        startActivity(BookDetailActivity.newIntent(this,bookName,listId,"BookSearchActivity"))
    }

    override fun onTapLibraryBookOption(book: BookVO) {
    }
}