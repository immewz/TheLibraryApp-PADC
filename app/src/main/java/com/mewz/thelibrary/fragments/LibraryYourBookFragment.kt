package com.mewz.thelibrary.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mewz.thelibrary.R
import com.mewz.thelibrary.activities.AddToShelvesActivity
import com.mewz.thelibrary.activities.BookDetailActivity
import com.mewz.thelibrary.data.vos.overview.BookVO
import com.mewz.thelibrary.databinding.FragmentLibraryYourBookBinding
import com.mewz.thelibrary.delegates.library.LibraryBookViewHolderDelegate
import com.mewz.thelibrary.mvp.presenters.LibraryYourBookPresenter
import com.mewz.thelibrary.mvp.presenters.LibraryYourBookPresenterImpl
import com.mewz.thelibrary.mvp.views.LibraryYourBookView
import com.mewz.thelibrary.views.viewpods.LibraryBookListViewPod

class LibraryYourBookFragment : Fragment(), LibraryYourBookView, LibraryBookViewHolderDelegate {

    private lateinit var binding: FragmentLibraryYourBookBinding

    private lateinit var mLibraryBookListViewPod: LibraryBookListViewPod

    private lateinit var mPresenter: LibraryYourBookPresenter

    private var mBookList: List<BookVO> = listOf()
    private var mListNameList: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_library_your_book, container, false)

        binding = FragmentLibraryYourBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpPresenter()

        setUpViewPod()
        setUpListeners()

        mPresenter.onUiReady(this)

    }


    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(requireActivity())[LibraryYourBookPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpListeners() {
        binding.vpLibraryBook.btnViewBooks.setOnClickListener {
            mPresenter.onTapView()
        }

        binding.vpLibraryBook.btnSortByLibrary.setOnClickListener {
            mPresenter.onTapSort()
        }

        binding.vpLibraryBook.btnClearChip.setOnClickListener {
            mPresenter.onTapClose()
        }
    }


    private fun setUpViewPod() {
        mLibraryBookListViewPod = binding.vpLibraryBook.root
        mLibraryBookListViewPod.setDelegate(1, this)
        mLibraryBookListViewPod.setDelegateForChip(mPresenter)
    }

    override fun showBooksInLibrary(bookList: List<BookVO>?) {
        mBookList = bookList ?: listOf()
        mLibraryBookListViewPod.setNewData(mBookList)

        for(book in bookList!!) {
            mListNameList.add(book.listName ?: "")
        }
        mLibraryBookListViewPod.setChipData(mListNameList)
    }

    override fun showBookListByListName(bookList: List<BookVO>?) {
        mLibraryBookListViewPod.setNewData(bookList)
    }

    override fun showBottomSheetDialogForView() {
        val dialog = BottomSheetDialog(requireActivity())
        dialog.setContentView(R.layout.bottom_sheet_view)
        dialog.show()

        dialog.findViewById<RadioButton>(R.id.rbList)?.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                mLibraryBookListViewPod.setDelegate(1, this)
                dialog.dismiss()
            }
        }

        dialog.findViewById<RadioButton>(R.id.rbLargeGrid)?.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                mLibraryBookListViewPod.setDelegate(2, this)
                dialog.dismiss()
            }
        }

        dialog.findViewById<RadioButton>(R.id.rbSmallGrid)?.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                mLibraryBookListViewPod.setDelegate(3, this)
                dialog.dismiss()
            }
        }
    }


    @SuppressLint("SetTextI18n")
    override fun showBottomSheetDialogForSorting() {
        val dialog = BottomSheetDialog(requireActivity())
        dialog.setContentView(R.layout.bottom_sheet_sortby)
        dialog.show()

        dialog.findViewById<RadioButton>(R.id.rbRecent)?.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                binding.vpLibraryBook.tvSortingMethod.text = "Recent"
                mLibraryBookListViewPod.setNewData(mBookList)
                dialog.dismiss()
            }
        }

        dialog.findViewById<RadioButton>(R.id.rbTitle)?.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                binding.vpLibraryBook.tvSortingMethod.text = "Title"
                mLibraryBookListViewPod.setNewData(mPresenter.sortByTitle())
                dialog.dismiss()
            }
        }

        dialog.findViewById<RadioButton>(R.id.rbAuthor)?.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                binding.vpLibraryBook.tvSortingMethod.text = "Author"
                mLibraryBookListViewPod.setNewData(mPresenter.sortByAuthor())
                dialog.dismiss()
            }
        }
    }

    override fun onTapChip(listName: String) {
        mPresenter.onUiReadyForListName(this,listName)
    }

    override fun onTapCloseAllChip() {
        mLibraryBookListViewPod.setNewData(mBookList)
        mLibraryBookListViewPod.clearChip(true)
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun onTapLibraryBook(bookName: String, listId: Int) {
        startActivity(context?.let { BookDetailActivity.newIntent(it,bookName,listId,"") })
    }

    override fun onTapLibraryBookOption(book: BookVO) {
        val dialog = BottomSheetDialog(requireActivity())
        dialog.setContentView(R.layout.bottom_sheet_option)
        dialog.show()

        dialog.findViewById<AppCompatImageView>(R.id.ivBookImage)?.let {
            Glide.with(requireActivity())
                .load(book.bookImage)
                .into(it)
        }

        dialog.findViewById<AppCompatTextView>(R.id.tvBookTitle)?.text = book.title
        dialog.findViewById<AppCompatTextView>(R.id.tvAuthor)?.text = book.author

        dialog.findViewById<LinearLayout>(R.id.btnBottomSheetDownload)?.setOnClickListener {
            Toast.makeText(requireActivity(),"Downloading",Toast.LENGTH_SHORT).show()
        }

        dialog.findViewById<LinearLayout>(R.id.btnBottomSheetAddToLibrary)?.visibility = View.GONE

        dialog.findViewById<LinearLayout>(R.id.btnBottomSheetDeleteLibraryBook)?.setOnClickListener {
            val alertDialog = MaterialAlertDialogBuilder(requireActivity(),R.style.RoundedAlertDialog)
                .setTitle("Delete Book !")
                .setMessage("Are you sure ?")
                .setPositiveButton("Yes") { alertDialog , _ ->
                    mPresenter.deleteBookByTitle(book.title)
                    Toast.makeText(requireActivity(),"Book's removed",Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                    alertDialog.dismiss()
                }
                .setNegativeButton("Cancel") { alertDialog , _ ->
                    alertDialog.dismiss()
                }.create()

            alertDialog.show()
        }

        dialog.findViewById<LinearLayout>(R.id.btnBottomSheetDeleteShelvesBook)?.visibility = View.GONE

        dialog.findViewById<LinearLayout>(R.id.btnBottomSheetAddToShelve)?.setOnClickListener {
            startActivity(context?.let { AddToShelvesActivity.newIntent(it, book.title) })
            dialog.dismiss()
        }

        dialog.findViewById<LinearLayout>(R.id.btnBottomSheetRead)?.setOnClickListener {
            Toast.makeText(requireActivity(),"Marks as read",Toast.LENGTH_SHORT).show()
        }

        dialog.findViewById<LinearLayout>(R.id.btnBottomSheetAbout)?.setOnClickListener {
            Toast.makeText(requireActivity(),"About this ebook",Toast.LENGTH_SHORT).show()
        }
    }

}