package com.jjg.testmvvm.ui.activity

import android.content.res.Configuration
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jjg.testmvvm.R
import com.jjg.testmvvm.databinding.ActivitySearchBinding
import com.jjg.testmvvm.model.network.core.INetworkListener
import com.jjg.testmvvm.model.network.set.NetworkConstants
import com.jjg.testmvvm.model.util.log.Log
import com.jjg.testmvvm.ui.adapter.SearchAdapter
import com.jjg.testmvvm.ui.common.activity.BaseMvvmActivity
import com.jjg.testmvvm.viewModel.SearchVm


class SearchActivity : BaseMvvmActivity<ActivitySearchBinding, SearchVm>(
    R.layout.activity_search, SearchVm::class.java
) {
    private lateinit var adapter: SearchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setListener()
        initView()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        binding.invalidateAll()
    }
    override fun bindViewModel() {
        viewModel.searchList.observe(this, {
            adapter.submitList(it)
        })

        binding.viewModel = viewModel
    }

    private fun initView() {
        adapter = SearchAdapter()
        binding.rvSearch.adapter = adapter
        binding.rvSearch.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setListener() {
        setNetworkListener(object : INetworkListener {
            override fun onNoneListener() {
                binding.invalidateAll()
            }

            override fun onPrepareListener() {

            }

            override fun onFailListener(title: String, message: String) {
                Log.e("================== title : $title , message : $message")
                showNetworkDialog(title, message)
            }

            override fun onSuccessListener(url: String) {
                if (url.contains(NetworkConstants.URL_SEARCH)) {
                    binding.invalidateAll()
                }
            }
        })
    }
}

