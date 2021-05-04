package com.jjg.testmvvm.ui.common.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jjg.testmvvm.model.network.core.INetworkListener
import com.jjg.testmvvm.model.network.core.STATUS
import com.jjg.testmvvm.model.network.set.NetworkStatus
import com.jjg.testmvvm.model.util.log.Log
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

    private var networkListener: INetworkListener? = null

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
                STATUS.NONE -> {
                    Log.d("Observer onNoneListener")
                    if (null != networkListener)
                        networkListener?.onNoneListener()
                }
                STATUS.PREPARED -> {
                    Log.d("Observer OnPrepareListener")
                    showLoadingDialog()
                    if (null != networkListener)
                        networkListener?.onPrepareListener()
                }
                STATUS.FAIL -> {
                    Log.d("Observer OnFailListener")
                    closeLoadingDialog()
                    if (null != networkListener)
                        networkListener?.onFailListener(
                            networkStatus.errorTitle,
                            networkStatus.errorContent
                        )
                }
                STATUS.SUCCESS -> {
                    Log.d("Observer OnSuccessListener")
                    closeLoadingDialog()
                    networkListener?.onSuccessListener(url)
                }
            }
        }
        viewModel.statusNetwork.observe(this, statusNetworkObserver)
    }

    abstract fun bindViewModel()

    fun setNetworkListener(networkListener: INetworkListener) {
        this.networkListener = networkListener
    }
}
