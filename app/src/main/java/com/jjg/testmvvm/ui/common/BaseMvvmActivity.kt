package com.jjg.testmvvm.ui.common

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jjg.testmvvm.model.network.core.INetworkListener
import com.jjg.testmvvm.model.network.core.STATUS
import com.jjg.testmvvm.model.network.set.NetworkStatus
import com.jjg.testmvvm.viewModel.common.BaseVm

/**
 * MVVM 패턴을 사용하는 BaseMvvmActivity
 *
 * 누락될수있는 DataBinding 및 ViewModel을 공통으로 처리해줌
 *
 */
abstract class BaseMvvmActivity<V : ViewDataBinding, VM : BaseVm>(
    @LayoutRes private val layoutResId: Int,
    private val viewModelName: Class<VM>
) : BaseActivity() {
    private var TAG = javaClass.name

    protected lateinit var binding: V
    protected lateinit var viewModel: VM

    private var networkListener:INetworkListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(viewModelName)

        binding.lifecycleOwner = this
        observerNetworkStatus()
        bindViewModel()
    }

    private fun observerNetworkStatus() {
        val statusNetworkObserver = Observer<NetworkStatus> { networkStatus ->

            val status = networkStatus.status
            val url = networkStatus.url
            when (status) {
                STATUS.PREPARED -> {
                    Log.d(TAG, "Observer OnPrepareListener")
                    networkListener?.onPrepareListener()
                }
                STATUS.FAIL -> {
                    Log.d(TAG, "Observer OnFailListener")
                    networkListener?.onFailListener()
                }
                STATUS.SUCCESS -> {
                    Log.d(TAG, "Observer OnSuccessListener")
                    networkListener?.onSuccessListener(url)
                }
            }
        }

        viewModel.statusNetwork.observe(this, statusNetworkObserver)

    }

    abstract fun bindViewModel()

    fun setNetworkListener(networkListener: INetworkListener){
        this.networkListener = networkListener
    }
}
