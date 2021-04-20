package com.jjg.testmvvm.viewModel.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jjg.testmvvm.model.network.core.STATUS
import com.jjg.testmvvm.model.network.set.NetworkStatus

open class BaseVm: ViewModel() {
    val statusNetwork: MutableLiveData<NetworkStatus> by lazy {
        MutableLiveData<NetworkStatus>()
    }

    override fun onCleared() {
        super.onCleared()
    }


}