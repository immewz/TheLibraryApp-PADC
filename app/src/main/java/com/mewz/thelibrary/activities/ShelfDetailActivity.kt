package com.mewz.thelibrary.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mewz.thelibrary.R
import com.mewz.thelibrary.data.vos.ShelfVO
import com.mewz.thelibrary.data.vos.overview.BookVO
import com.mewz.thelibrary.databinding.ActivityShelfDetailBinding
import com.mewz.thelibrary.delegates.library.LibraryBookViewHolderDelegate
import com.mewz.thelibrary.mvp.presenters.ShelfDetailPresenter
import com.mewz.thelibrary.mvp.presenters.ShelfDetailPresenterImpl
import com.mewz.thelibrary.mvp.views.ShelfDetailView
import com.mewz.thelibrary.views.viewpods.LibraryBookListViewPod

class ShelfDetailActivity : AppCompatActivity(), ShelfDetailView, LibraryBookViewHolderDelegate {

    private lateinit var binding: ActivityShelfDetailBinding

    private lateinit var mShelfBookViewPod: LibraryBookListViewPod

    private lateinit var mPresenter: ShelfDetailPresenter

    private var mShelfId:Int = 0
    private var mBookCount = 0
    private var mBookList:MutableList<BookVO> = mutableListOf()
    private var mIsChecked:Boolean = false

    private var mShelfName:String = ""
    private var mListNameList:MutableList<String> = mutableListOf()

    companion object {
        private const val EXTRA_SHELF_ID = "EXTRA_SHELF_ID"
        fun newIntent(context: Context, shelfId: Int) : Intent {
            val intent = Intent(context,ShelfDetailActivity::class.java)
            intent.putExtra(EXTRA_SHELF_ID, shelfId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShelfDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpPresenter()

        setUpViewPod()
        setUpListeners()

        mShelfId = intent?.extras?.getInt(EXTRA_SHELF_ID) ?: 0

        mPresenter.onUiReadyForShelfDetail(this, mShelfId)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[ShelfDetailPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpListeners() {
        binding.btnBackShelves.setOnClickListener {
            val etShelfName = binding.etShelfNameShelfDetail.text.toString()
            val shelf = ShelfVO(mShelfId,etShelfName,mBookCount,mBookList,mIsChecked)
            mPresenter.updateShelf(shelf)
            mPresenter.onTapBack()
        }

        binding.btnShelfDetailOption.setOnClickListener {
            mPresenter.onTapOption()
        }

        binding.vpShelfBookList.btnViewBooks.setOnClickListener {
            mPresenter.onTapView()
        }

        binding.vpShelfBookList.btnSortByLibrary.setOnClickListener {
            mPresenter.onTapSort()
        }

        binding.vpShelfBookList.btnClearChip.setOnClickListener {
            mPresenter.onTapClearChip()
        }
    }

    private fun setUpViewPod() {
        mShelfBookViewPod = binding.vpShelfBookList.root
        mShelfBookViewPod.setDelegate(3, this)
        mShelfBookViewPod.setDelegateForChip(mPresenter)
    }

    override fun showBottomSheetDialogForShelfTitle() {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.bottom_sheet_shelf_detail)
        dialog.show()

        dialog.findViewById<LinearLayout>(R.id.btnBottomSheetRename)?.setOnClickListener {
            dialog.dismiss()
            binding.etShelfNameShelfDetail.visibility = View.VISIBLE

            val netEtShelfName = binding.tvShelfNameShelfDetail.text.toString()
            binding.tvShelfNameShelfDetail.visibility = View.GONE

            binding.etShelfNameShelfDetail.setText(netEtShelfName)

            binding.etShelfNameShelfDetail.setOnEditorActionListener { _, actionId, _ ->
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    val etShelfName = binding.etShelfNameShelfDetail.text.toString()
                    val shelf = ShelfVO(mShelfId,etShelfName,mBookCount,mBookList,mIsChecked)
                    mPresenter.updateShelf(shelf)
                    finish()
                    true
                } else {
                    false
                }
            }
        }

        dialog.findViewById<LinearLayout>(R.id.btnBottomSheetDelete)?.setOnClickListener{
            val alertDialog = MaterialAlertDialogBuilder(this,R.style.RoundedAlertDialog)
                .setTitle("Delete Shelf !")
                .setMessage("Are you sure ?")
                .setPositiveButton("Yes"){ alertDialog, _ ->
                    mPresenter.deleteShelf(mShelfId)
                    dialog.dismiss()
                    alertDialog.dismiss()
                    finish()
                }
                .setNegativeButton("Cancel"){ alertDialog , _ ->
                    dialog.dismiss()
                    alertDialog.dismiss()
                }
                .create()

            alertDialog.show()
        }
    }

    override fun showBottomSheetDialogForView() {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.bottom_sheet_view)
        dialog.show()

        dialog.findViewById<RadioButton>(R.id.rbList)?.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                mShelfBookViewPod.setDelegate(1, this)
                dialog.dismiss()
            }
        }

        dialog.findViewById<RadioButton>(R.id.rbLargeGrid)?.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                mShelfBookViewPod.setDelegate(2, this)
                dialog.dismiss()
            }
        }

        dialog.findViewById<RadioButton>(R.id.rbSmallGrid)?.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                mShelfBookViewPod.setDelegate(3, this)
                dialog.dismiss()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun showBottomSheetDialogForSorting() {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.bottom_sheet_sortby)
        dialog.show()

        dialog.findViewById<RadioButton>(R.id.rbRecent)?.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                binding.vpShelfBookList.tvSortingMethod.text = "Recent"
                mShelfBookViewPod.setNewData(mBookList)
                dialog.dismiss()
            }
        }

        dialog.findViewById<RadioButton>(R.id.rbTitle)?.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                binding.vpShelfBookList.tvSortingMethod.text = "Title"
                mShelfBookViewPod.setNewData(mPresenter.sortByTitle())
                dialog.dismiss()
            }
        }

        dialog.findViewById<RadioButton>(R.id.rbAuthor)?.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                binding.vpShelfBookList.tvSortingMethod.text = "Author"
                mShelfBookViewPod.setNewData(mPresenter.sortByAuthor())
                dialog.dismiss()
            }
        }
    }

    override fun showShelfDetail(shelfVO: ShelfVO?) {
        mBookCount = shelfVO?.bookCount ?: 0
        mBookList = shelfVO?.bookList ?: mutableListOf()
        mShelfName = shelfVO?.shelfName ?: ""

        binding.tvShelfNameShelfDetail.text = shelfVO?.shelfName ?: ""

        var bookCount = "${shelfVO?.bookCount} books"
        if(shelfVO?.bookCount == 0) {
            bookCount = "0 book"
        }
        binding.tvBookCount.text = bookCount

        shelfVO?.bookList?.let {
            mShelfBookViewPod.setNewData(it)
        }

        for(book in mBookList) {
            mListNameList.add(book.listName ?: "")
        }
        mShelfBookViewPod.setChipData(mListNameList)
    }

    override fun navigateToBack() {
        onBackPressed()
    }

    override fun onTapChip(listName: String) {
        val books = mutableListOf<BookVO>()
        for(book in mBookList) {
            if(listName == book.listName) {
                books.add(book)
            }
        }
        mShelfBookViewPod.setNewData(books)
    }

    override fun onTapClearChip() {
        mShelfBookViewPod.setNewData(mBookList)
        mShelfBookViewPod.clearChip(true)
    }

    override fun showError(error: String) {
        Toast.makeText(this,error, Toast.LENGTH_SHORT).show()
    }

    override fun onTapLibraryBook(bookName: String, listId: Int) {
        startActivity(BookDetailActivity.newIntent(this, bookName, listId, "ShelfDetailActivity"))
    }

    override fun onTapLibraryBookOption(book: BookVO) {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.bottom_sheet_option)
        dialog.show()

        dialog.findViewById<AppCompatImageView>(R.id.ivBookImage)?.let {
            Glide.with(this)
                .load(book.bookImage)
                .into(it)
        }

        dialog.findViewById<AppCompatTextView>(R.id.tvBookTitle)?.text = book.title
        dialog.findViewById<AppCompatTextView>(R.id.tvAuthor)?.text = book.author

        dialog.findViewById<LinearLayout>(R.id.btnBottomSheetDeleteShelvesBook)?.setOnClickListener {
            val alertDialog = MaterialAlertDialogBuilder(this,R.style.RoundedAlertDialog)
                .setTitle("Delete Book !")
                .setMessage("Are you sure ?")
                .setPositiveButton("Yes"){ alertDialog, _ ->
                    mBookList.remove(book)
                    mShelfBookViewPod.setNewData(mBookList)

                    mBookCount = mBookList.size
                    binding.tvBookCount.text = mBookCount.toString()

                    val shelf = ShelfVO(mShelfId,mShelfName,mBookCount,mBookList,mIsChecked)
                    mPresenter.updateShelf(shelf)

                    dialog.dismiss()
                    alertDialog.dismiss()
                }
                .setNegativeButton("Cancel"){ alertDialog , _ ->
                    dialog.dismiss()
                    alertDialog.dismiss()
                }
                .create()

            alertDialog.show()
        }

        dialog.findViewById<LinearLayout>(R.id.btnBottomSheetAddToShelve)?.visibility = View.GONE

        dialog.findViewById<LinearLayout>(R.id.btnBottomSheetRead)?.setOnClickListener {
            Toast.makeText(this,"Marks as read",Toast.LENGTH_SHORT).show()
        }

        dialog.findViewById<LinearLayout>(R.id.btnBottomSheetAbout)?.setOnClickListener {
            Toast.makeText(this,"About this ebook",Toast.LENGTH_SHORT).show()
        }
    }
}