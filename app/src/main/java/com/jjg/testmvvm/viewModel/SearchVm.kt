package com.jjg.testmvvm.viewModel

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jjg.testmvvm.model.dataSourceFactory.DataSourceFactory
import com.jjg.testmvvm.model.network.NetworkRequest
import com.jjg.testmvvm.model.network.core.STATUS
import com.jjg.testmvvm.model.network.set.NetworkConstants
import com.jjg.testmvvm.model.network.set.NetworkStatus
import com.jjg.testmvvm.model.network.vo.resp.Document
import com.jjg.testmvvm.model.network.vo.resp.VoSearch
import com.jjg.testmvvm.model.util.log.Log
import com.jjg.testmvvm.viewModel.common.BaseVm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchVm : BaseVm() {
    val voSearch: MutableLiveData<ArrayList<Document>> by lazy {
        MutableLiveData<ArrayList<Document>>()
    }

    val strSearch: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        val pageListConfig = PagedList.Config.Builder()

        pageListConfig
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)

        val dataSourceFactory = DataSourceFactory()
        val dataSource = dataSourceFactory.create()

        var list = LivePagedListBuilder<Int,Document>(dataSourceFactory, pageListConfig.build()).build()


    }

    private fun search(url: String) {
        statusNetwork.value = NetworkStatus(url, STATUS.PREPARED)

        NetworkRequest.getInstance()
            .requestSearch(strSearch.value!!, object : Callback<VoSearch> {
                override fun onFailure(call: Call<VoSearch>, t: Throwable) {
                    Log.d("========= fail ==============")
                    Log.d("${t.message}")
                    Log.d("=======================")
                    searchFail(url)
                }

                override fun onResponse(
                    call: Call<VoSearch>,
                    response: Response<VoSearch>
                ) {
                    Log.d("===========success============")
                    if (response.isSuccessful) {
                        voSearch.postValue(response.body()!!.documents)
                        statusNetwork.value = NetworkStatus(url, STATUS.SUCCESS)
                    } else {
                        searchFail(url)
                    }
                }
            })
    }

    private fun searchFail(url: String) {
        voSearch.value!!.clear()
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
            var item = voSearch.value!!
            item == null || item.size == 0
        }
    }
}