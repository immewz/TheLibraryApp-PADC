package com.mewz.thelibrary.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mewz.thelibrary.R
import com.mewz.thelibrary.data.vos.ShelfVO
import com.mewz.thelibrary.databinding.ActivityNewShelfBinding
import com.mewz.thelibrary.mvp.presenters.NewShelfPresenter
import com.mewz.thelibrary.mvp.presenters.NewShelfPresenterImpl
import com.mewz.thelibrary.mvp.views.NewShelfView

class NewShelfActivity : AppCompatActivity(), NewShelfView {

    private lateinit var binding: ActivityNewShelfBinding

    private lateinit var mPresenter: NewShelfPresenter

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, NewShelfActivity::class.java)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewShelfBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpPresenter()

        setUpEditText()
        setUpListeners()

        mPresenter.onUiReady(this)
    }


    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[NewShelfPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpListeners() {
        binding.btnBackCreateShelf.setOnClickListener {
            val title = ShelfVO(shelfName = binding.etCreateShelf.text.toString())
            mPresenter.insertShelf(title)
            mPresenter.onTapBack()
        }
    }

    private fun setUpEditText() {

        binding.etCreateShelf.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val charCount = s.toString().length
                val characterCountString = "$charCount/50"
                binding.tvCountNewShelf.text = characterCountString
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.etCreateShelf.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE){
                val shelf = ShelfVO(shelfName = binding.etCreateShelf.text.toString())
                mPresenter.insertShelf(shelf)
                finish()
                true
            }else{
                false
            }
        }
    }

    override fun navigateToBackScreen() {
        onBackPressed()
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}