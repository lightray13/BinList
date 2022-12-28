package com.test.binlist.ui.binList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.binlist.R
import com.test.binlist.adapter.CardListAdapter
import com.test.binlist.databinding.ActivityBinListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BinListActivity : AppCompatActivity() {
    private val viewModel: BinListViewModel by viewModels()

    private lateinit var binding: ActivityBinListBinding

    private var cardListAdapter = CardListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bin_list)

        binding = ActivityBinListBinding.inflate(layoutInflater).also { setContentView(it.root) }

        supportActionBar?.title = resources.getString(R.string.history)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        observeViewModel()
        initializeViews()
    }

    private fun initializeViews() {
        binding.cardListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cardListAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.binList.observe(this) {
            cardListAdapter.updateList(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}