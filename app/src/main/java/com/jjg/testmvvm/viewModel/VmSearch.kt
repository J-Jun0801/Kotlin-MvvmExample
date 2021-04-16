package com.jjg.testmvvm.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jjg.testmvvm.model.network.NetworkRequest
import com.jjg.testmvvm.model.network.core.INetworkListener
import com.jjg.testmvvm.model.network.vo.resp.Document
import com.jjg.testmvvm.model.network.vo.resp.VoSearch
import com.jjg.testmvvm.viewModel.common.VmBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VmSearch : VmBase() {

    val voSearch: MutableLiveData<VoSearch> by lazy {
        MutableLiveData<VoSearch>()
    }

    val strSearch: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun search(networkListener: INetworkListener) {
        networkListener.onPrepareListener()

        NetworkRequest.getInstance()
            .requestSearch(strSearch.value!!, object : Callback<VoSearch> {
                override fun onFailure(call: Call<VoSearch>, t: Throwable) {
                    Log.d("TAG", "========= fail ==============")
                    Log.d("TAG", "${t.message}")
                    Log.d("TAG", "=======================")
                    searchFail(networkListener)
                }

                override fun onResponse(
                    call: Call<VoSearch>,
                    response: Response<VoSearch>
                ) {
                    Log.d("TAG", "===========success============")
                    Log.d("TAG", "${response.body()!!.meta}")
                    Log.d("TAG", "=======================")

                    if (response.isSuccessful) {
                        voSearch.postValue(response.body())
                        networkListener.onSuccessListener()
                    } else {
                        searchFail(networkListener)
                    }
                }
            })
    }

    private fun searchFail(networkListener: INetworkListener) {
        voSearch.value!!.documents.clear()
        networkListener.onFailListener()
    }

    fun isEmpty(): Boolean {
        return strSearch.value!!.isEmpty()
    }

    fun setStrSearch(str: String) {
        strSearch.value = str
    }
}