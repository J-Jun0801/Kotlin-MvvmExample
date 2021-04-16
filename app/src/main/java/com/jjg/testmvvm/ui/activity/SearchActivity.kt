package com.jjg.testmvvm.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.jjg.testmvvm.R
import com.jjg.testmvvm.databinding.ActivitySearchBinding
import com.jjg.testmvvm.model.network.core.INetworkListener
import com.jjg.testmvvm.ui.adapter.SearchAdapter
import com.jjg.testmvvm.ui.common.BaseMvvmActivity
import com.jjg.testmvvm.viewModel.VmSearch


class SearchActivity : BaseMvvmActivity<ActivitySearchBinding, VmSearch>(
    R.layout.activity_search, VmSearch::class.java
) {

    private val TAG = javaClass.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun bindViewModel() {
        binding.viewModel = viewModel
    }

    private fun initView() {
        binding.btnSearch.setOnClickListener {
            viewModel.setStrSearch(binding.etSearch.text.toString())
            if (viewModel.isEmpty()) {
                return@setOnClickListener
            }
            requestSearch()
        }

        binding.rvSearch.adapter = SearchAdapter(this, null)
        binding.rvSearch.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun requestSearch() {
        viewModel.search(object : INetworkListener {
            override fun onPrepareListener() {
                Log.d(TAG, "OnPrepareListener")
            }

            override fun onFailListener() {
                Log.d(TAG, "OnFailListener")
            }

            override fun onSuccessListener() {
                Log.d(TAG, "OnSuccessListener")
                (binding.rvSearch.adapter!! as SearchAdapter).setViewModel(binding.viewModel!!)
                binding.rvSearch.adapter!!.notifyDataSetChanged()
            }
        })
    }
}

