package com.jjg.testmvvm.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jjg.testmvvm.BR
import com.jjg.testmvvm.R
import com.jjg.testmvvm.databinding.ActivitySearchBinding
import com.jjg.testmvvm.model.network.core.INetworkListener
import com.jjg.testmvvm.model.network.set.NetworkConstants
import com.jjg.testmvvm.ui.adapter.SearchAdapter
import com.jjg.testmvvm.ui.common.BaseMvvmActivity
import com.jjg.testmvvm.viewModel.SearchVm


class SearchActivity : BaseMvvmActivity<ActivitySearchBinding, SearchVm>(
    R.layout.activity_search, SearchVm::class.java
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setListener()
        initView()
    }

    override fun bindViewModel() {
        binding.viewModel = viewModel
    }

    private fun initView() {
        binding.rvSearch.adapter = SearchAdapter(this, null)
        binding.rvSearch.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun setListener() {
        setNetworkListener(object : INetworkListener {
            override fun onPrepareListener() {

            }

            override fun onFailListener() {
            }

            override fun onSuccessListener(url: String) {
                if (url.contains(NetworkConstants.URL_SEARCH)) {
                    binding.invalidateAll()
                    (binding.rvSearch.adapter!! as SearchAdapter).setViewModel(binding.viewModel!!)
                    binding.rvSearch.adapter!!.notifyDataSetChanged()
                }
            }
        })
    }
}

