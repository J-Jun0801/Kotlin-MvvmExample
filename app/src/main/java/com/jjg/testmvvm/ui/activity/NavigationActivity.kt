package com.jjg.testmvvm.ui.activity

import android.content.res.Configuration
import com.jjg.testmvvm.R
import com.jjg.testmvvm.databinding.ActivityNavigationBinding
import com.jjg.testmvvm.ui.common.activity.BaseMvvmActivity
import com.jjg.testmvvm.viewModel.NavigationVm


class NavigationActivity : BaseMvvmActivity<ActivityNavigationBinding, NavigationVm>(
    R.layout.activity_navigation, NavigationVm::class.java
) {

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        binding.invalidateAll()
    }

    override fun bindViewModel() {
        binding.viewModel = viewModel
    }
}

