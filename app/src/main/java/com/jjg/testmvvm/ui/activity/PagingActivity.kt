package com.jjg.testmvvm.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jjg.testmvvm.R
import com.jjg.testmvvm.databinding.ActivityPagingBinding
import com.jjg.testmvvm.ui.common.activity.BaseMvvmActivity
import com.jjg.testmvvm.viewModel.PagingVm
import kotlinx.android.synthetic.main.activity_paging.*

class PagingActivity : BaseMvvmActivity<ActivityPagingBinding,PagingVm>(R.layout.activity_paging,PagingVm::class.java)
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(this)
    }
    override fun bindViewModel() {
        binding.viewModel = viewModel
    }
}