package com.jjg.testmvvm.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.jjg.testmvvm.R
import com.jjg.testmvvm.databinding.ActivityMenuBinding
import com.jjg.testmvvm.ui.common.activity.BaseMvvmActivity
import com.jjg.testmvvm.viewModel.MenuVm
import com.jjg.testmvvm.viewModel.MenuVm.Companion.EVENT_ID_NAVIGATION
import com.jjg.testmvvm.viewModel.MenuVm.Companion.EVENT_ID_SEARCH
import com.jjg.testmvvm.viewModel.MenuVm.Companion.EVENT_ID_TRANSLATE

class MenuActivity : BaseMvvmActivity<ActivityMenuBinding, MenuVm>(
    R.layout.activity_menu, MenuVm::class.java
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun bindViewModel() {
        val eventIdObserver = Observer<Int> { eventId ->
            when (eventId) {
                EVENT_ID_TRANSLATE -> {
                    startActivity(Intent(this, TranslateActivity::class.java))
                }
                EVENT_ID_SEARCH -> {
                    startActivity(Intent(this, SearchActivity::class.java))
                }
                EVENT_ID_NAVIGATION -> {

                }
            }
        }
        viewModel.eventId.observe(this, eventIdObserver)
        binding.viewModel = viewModel
    }
}