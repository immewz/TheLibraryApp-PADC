package com.mewz.thelibrary.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mewz.thelibrary.R
import com.mewz.thelibrary.adapters.BookReviewAdapter
import com.mewz.thelibrary.data.vos.SearchBookVO
import com.mewz.thelibrary.data.vos.list.BookDetailVO
import com.mewz.thelibrary.data.vos.overview.BookCategoryVO
import com.mewz.thelibrary.data.vos.overview.BookVO
import com.mewz.thelibrary.databinding.ActivityBookDetailBinding
import com.mewz.thelibrary.mvp.presenters.BookDetailPresenter
import com.mewz.thelibrary.mvp.presenters.BookDetailPresenterImpl
import com.mewz.thelibrary.mvp.views.BookDetailView

class BookDetailActivity : AppCompatActivity(), BookDetailView {

    private lateinit var binding: ActivityBookDetailBinding

    private lateinit var mBookReviewAdapter: BookReviewAdapter

    private lateinit var mPresenter: BookDetailPresenter

    private lateinit var mBookName: String
    private lateinit var mAboutBook: String

    companion object {
        private const val EXTRA_BOOK_NAME = "EXTRA_BOOK_NAME"
        private const val EXTRA_LIST_ID = "EXTRA_LIST_ID"
        private const val EXTRA_PREVIOUS_PLACE = "EXTRA_PREVIOUS_PLACE"

        fun newIntent(context: Context, bookName:String, listId:Int, previousPlace:String) : Intent {
            val intent = Intent(context,BookDetailActivity::class.java)
            intent.putExtra(EXTRA_BOOK_NAME,bookName)
            intent.putExtra(EXTRA_LIST_ID,listId)
            intent.putExtra(EXTRA_PREVIOUS_PLACE,previousPlace)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpPresenter()

        setUpRecyclerView()
        setUpListeners()

        mBookName = intent?.getStringExtra(EXTRA_BOOK_NAME) ?: ""
        val listId = intent?.getIntExtra(EXTRA_LIST_ID, 0) ?: 0
        val previousPlace = intent?.getStringExtra(EXTRA_PREVIOUS_PLACE) ?: ""

        mPresenter.onUiReadyForBookDetail(this,mBookName,listId,previousPlace)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[BookDetailPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpListeners() {
        binding.btnBackBookDetail.setOnClickListener {
            mPresenter.onTapBack()
        }

        binding.btnAbout.setOnClickListener {
            mPresenter.onTapAboutBook()
        }

        binding.btnRating.setOnClickListener {
            mPresenter.onTapRatingAndReview()
        }

    }

    private fun setUpRecyclerView() {
        mBookReviewAdapter = BookReviewAdapter()
        binding.rvReview.adapter = mBookReviewAdapter
        binding.rvReview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun getCategoryByName(category: BookCategoryVO) {
        for(book in category.books!!) {
            if(mBookName == book.title) {
                bindData(book)
                book.listId = category.listId
                book.listName = category.listName ?: ""
                book.bookImage = book.bookImage?.replace("http://", "https://")
                mPresenter.insertBookIntoLibrary(book)
                break
            }
        }
    }

    private fun bindData(book: BookVO) {
        Glide.with(this)
            .load(book.bookImage)
            .into(binding.ivBook)

        binding.tvBookTitle.text = book.title
        binding.tvAuthor.text = book.author
        binding.tvOverview.text = book.description

        mBookName = book.title
        mAboutBook = book.description.toString()
    }

    override fun getBookFromBookList(bookDetail: BookDetailVO) {
        binding.tvBookTitle.text = bookDetail.title
        binding.tvAuthor.text = bookDetail.author
        binding.tvOverview.text = bookDetail.description

        mBookName = bookDetail.title.toString()
        mAboutBook = bookDetail.description.toString()
    }

    override fun getAllBooksFromLibrary(bookList: List<BookVO>) {
        for(book in bookList) {
            if(mBookName == book.title) {

                Glide.with(this)
                    .load(book.bookImage)
                    .into(binding.ivBook)

                binding.tvBookTitle.text = book.title
                binding.tvAuthor.text = book.author
                binding.tvOverview.text = book.description

                mBookName = book.title
                mAboutBook = book.description.toString()
                break
            }
        }
    }

    override fun navigateToRatingAndReviewScreen() {
        startActivity(BookRatingAndReviewActivity.newIntent(this))
    }

    override fun navigateToAboutBookScreen() {
        startActivity(AboutBookActivity.newIntent(this, mBookName, mAboutBook))
    }

    override fun navigateToBackScreen() {
        onBackPressed()
    }

    override fun showSearchBook(bookList: List<SearchBookVO>) {
        bookList.map {
            BookVO(
                title = it.title,
                author = it.author ?: "",
                description = it.description ?: "",
                ageGroup = null,
                amazonProductUrl = null,
                articleChapterLink = null,
                bookImage = it.image?.replace("http://", "https://"),
                bookImageHeight = null,
                bookImageWidth = null,
                bookReviewLink = null,
                contributor = null,
                contributorNote = null,
                createdDate = null,
                firstChapterLink = null,
                primaryIsbn10 = null,
                primaryIsbn13 = null,
                publisher = null,
                rank = null,
                rankLastWeek = null,
                sundayReviewLink = null,
                updatedDate = null,
                weeksOnList = null,
                listId = null,
                listName = null,
                bookUri = null,
                buyLinks = null,
                price = null
            )
        }.map { book ->
            if(mBookName == book.title) {
                mPresenter.insertBookIntoLibrary(book)
            }
        }

        for(book in bookList) {
            if(mBookName == book.title) {
                binding.tvBookTitle.text = book.title
                binding.tvAuthor.text = book.author
                binding.tvOverview.text = book.description

                val searchBook = book.image?.replace("http://", "https://")

                Glide.with(this)
                    .load(searchBook)
                    .into(binding.ivBook)

                mBookName = book.title
                mAboutBook = book.description.toString()
                break
            }
        }
    }

    override fun showError(error: String) {
        Toast.makeText(this,error, Toast.LENGTH_SHORT).show()
    }
}