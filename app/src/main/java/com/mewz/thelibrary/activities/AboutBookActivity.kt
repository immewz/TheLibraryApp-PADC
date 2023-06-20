package com.mewz.thelibrary.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mewz.thelibrary.R
import com.mewz.thelibrary.databinding.ActivityAboutBookBinding

class AboutBookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBookBinding

    companion object {
        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"
        fun newIntent(context: Context, title: String, description: String): Intent {
            val intent = Intent(context, AboutBookActivity::class.java)
            intent.putExtra(EXTRA_TITLE, title)
            intent.putExtra(EXTRA_DESCRIPTION, description)
            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent?.getStringExtra(EXTRA_TITLE) ?: ""
        val description = intent?.getStringExtra(EXTRA_DESCRIPTION) ?: ""

        binding.tvAboutTitle.text = title
        binding.tvAboutBook.text = description

        binding.btnBackAboutBook.setOnClickListener {
            onBackPressed()
        }
    }
}