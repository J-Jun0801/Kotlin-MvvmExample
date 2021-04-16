package com.jjg.testmvvm.ui.common

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * MVVM 패턴을 사용하는 BaseMvvmActivity
 *
 * 누락될수있는 DataBinding 및 ViewModel을 공통으로 처리해줌
 *
 */
abstract class BaseMvvmActivity<V : ViewDataBinding, VM : ViewModel>(
    @LayoutRes private val layoutResId: Int,
    private val viewModelName: Class<VM>
) : BaseActivity() {

    protected lateinit var binding: V
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(viewModelName)

        binding.lifecycleOwner = this
        bindViewModel()
    }

    abstract fun bindViewModel()

}
