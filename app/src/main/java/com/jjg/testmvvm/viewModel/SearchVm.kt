package com.jjg.testmvvm.viewModel

import android.util.Log
import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.InverseBindingAdapter
import androidx.lifecycle.MutableLiveData
import com.jjg.testmvvm.model.network.NetworkRequest
import com.jjg.testmvvm.model.network.core.STATUS
import com.jjg.testmvvm.model.network.set.NetworkConstants
import com.jjg.testmvvm.model.network.set.NetworkStatus
import com.jjg.testmvvm.model.network.vo.resp.VoSearch
import com.jjg.testmvvm.viewModel.common.BaseVm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchVm : BaseVm() {

    val voSearch: MutableLiveData<VoSearch> by lazy {
        MutableLiveData<VoSearch>()
    }

    val strSearch: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private fun search(url: String) {
        statusNetwork.value = NetworkStatus(url, STATUS.PREPARED)

        NetworkRequest.getInstance()
            .requestSearch(strSearch.value!!, object : Callback<VoSearch> {
                override fun onFailure(call: Call<VoSearch>, t: Throwable) {
                    Log.d("TAG", "========= fail ==============")
                    Log.d("TAG", "${t.message}")
                    Log.d("TAG", "=======================")
                    searchFail(url)
                }

                override fun onResponse(
                    call: Call<VoSearch>,
                    response: Response<VoSearch>
                ) {
                    Log.d("TAG", "===========success============")
                    if (response.isSuccessful) {
                        voSearch.postValue(response.body())
                        statusNetwork.value = NetworkStatus(url, STATUS.SUCCESS)
                    } else {
                        searchFail(url)
                    }
                }
            })
    }

    private fun searchFail(url: String) {
        voSearch.value!!.documents.clear()
        statusNetwork.value = NetworkStatus(url, STATUS.FAIL)
    }

    private fun isEmpty(): Boolean {
        return strSearch.value!!.isEmpty()
    }

    private fun setStrSearch(str: String) {
        strSearch.value = str
    }

    fun clickSearch(str: String) {
        var url = NetworkConstants.BASE_URL + NetworkConstants.URL_SEARCH
        statusNetwork.value = NetworkStatus(url, STATUS.NONE)
        setStrSearch(str)
        if (isEmpty()) {
            return
        }
        search(url)
    }

    fun isEmptyDocuments(): Boolean {
        return if (voSearch.value == null) {
            true
        } else {
            var item = voSearch.value!!.documents
            item == null || item.size == 0
        }
    }
}