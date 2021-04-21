package com.jjg.testmvvm.ui.activity

import android.os.Bundle
import com.jjg.testmvvm.R
import com.jjg.testmvvm.databinding.ActivityMenuBinding
import com.jjg.testmvvm.ui.common.activity.BaseMvvmActivity
import com.jjg.testmvvm.viewModel.MenuVm

class MenuActivity : BaseMvvmActivity<ActivityMenuBinding,MenuVm>(
    R.layout.activity_menu, MenuVm::class.java
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun bindViewModel() {
        binding.viewModel = viewModel
    }
}