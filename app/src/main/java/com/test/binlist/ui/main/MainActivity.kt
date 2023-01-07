package com.test.binlist.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import com.test.binlist.R
import com.test.binlist.databinding.ActivityMainBinding
import com.test.binlist.ui.binList.BinListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        observeViewModel()
        initializeViews()
    }

    private fun initializeViews() {
        binding.loadInfoButton.setOnClickListener {
            loadCard()
        }
        binding.binEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                return@setOnEditorActionListener loadCard()
            }
            return@setOnEditorActionListener false
        }
        binding.binEditText.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                val DRAWABLE_RIGHT = 2
                when (event?.action) {
                    MotionEvent.ACTION_UP -> if(event.getRawX() >= (binding.binEditText.getRight() - binding.binEditText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        binding.binEditText.setText("")
                        return true
                    }
                }
                return false
            }
        })
    }

    private fun loadCard(): Boolean {
        val bin = binding.binEditText.text.toString()
        return if (bin.isNotBlank()) {
            viewModel.loadCardFromApi(bin)
            true
        } else {
            binding.binEditText.error = "Bin number is empty!"
            false
        }
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(this) {
            binding.binLoading.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.cardStock.observe(this) {
            it?.let {
                showToast("Card ${it.bin} was added successfully")
                startActivity(Intent(this, BinListActivity::class.java))
            }
        }

        viewModel.toastError.observe(this) {
            showToast(it, Toast.LENGTH_LONG)
        }
    }

    private fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }
}