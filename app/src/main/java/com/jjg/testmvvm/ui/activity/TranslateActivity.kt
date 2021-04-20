package com.jjg.testmvvm.ui.activity

import android.os.Bundle
import com.jjg.testmvvm.R
import com.jjg.testmvvm.databinding.ActivityTranslateBinding
import com.jjg.testmvvm.ui.common.activity.BaseMvvmActivity
import com.jjg.testmvvm.viewModel.TranslateVm

class TranslateActivity : BaseMvvmActivity<ActivityTranslateBinding, TranslateVm>(
    R.layout.activity_translate, TranslateVm::class.java
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun bindViewModel() {
        binding.viewModel = viewModel
    }
}