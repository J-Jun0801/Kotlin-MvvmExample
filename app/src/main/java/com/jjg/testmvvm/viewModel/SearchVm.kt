package com.jjg.testmvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.jjg.testmvvm.model.network.core.STATUS
import com.jjg.testmvvm.model.network.set.NetworkStatus
import com.jjg.testmvvm.model.network.vo.resp.Document
import com.jjg.testmvvm.model.repository.NetworkRepository
import com.jjg.testmvvm.viewModel.common.BaseVm
import com.jjg.testmvvm.viewModel.vo.SearchResultVo


class SearchVm : BaseVm() {
    private val repository: NetworkRepository = NetworkRepository()

    val voSearch: MutableLiveData<ArrayList<Document>> by lazy {
        MutableLiveData<ArrayList<Document>>()
    }

    val strSearch: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val repoResult: LiveData<SearchResultVo> = Transformations.map(strSearch) {
        repository.search(it)
    }

    val repos: LiveData<PagedList<Document>> = Transformations.switchMap(repoResult,
        { it->it.data })

    private fun search(url: String) {
//        statusNetwork.value = NetworkStatus(url, STATUS.PREPARED)
//
//        NetworkRequest.getInstance()
//            .requestSearch(strSearch.value!!, object : Callback<VoSearch> {
//                override fun onFailure(call: Call<VoSearch>, t: Throwable) {
//                    Log.d("========= fail ==============")
//                    Log.d("${t.message}")
//                    Log.d("=======================")
//                    searchFail(url)
//                }
//
//                override fun onResponse(
//                    call: Call<VoSearch>,
//                    response: Response<VoSearch>
//                ) {
//                    Log.d("===========success============")
//                    if (response.isSuccessful) {
//                        voSearch.postValue(response.body()!!.documents)
//                        statusNetwork.value = NetworkStatus(url, STATUS.SUCCESS)
//                    } else {
//                        searchFail(url)
//                    }
//                }
//            })
    }

    private fun search() {

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

//    fun clickSearch(str: String) {
//        var url = NetworkConstants.BASE_URL + NetworkConstants.URL_SEARCH
//        statusNetwork.value = NetworkStatus(url, STATUS.NONE)
//        setStrSearch(str)
//        if (isEmpty()) {
//            return
//        }
//        search(url)
//    }

    fun clickSearch(str: String) {
        setStrSearch(str)
        if (isEmpty()) {
            return
        }

        repoResult.value == null
    }

    fun isEmptyDocuments(): Boolean {
//        return if (voSearch.value == null) {
//            true
//        } else {
//            var item = voSearch.value!!
//            item == null || item.size == 0
//        }
        return false
    }
}