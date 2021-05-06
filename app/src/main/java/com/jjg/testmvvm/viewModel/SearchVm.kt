package com.jjg.testmvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.jjg.testmvvm.model.network.core.STATUS
import com.jjg.testmvvm.model.network.set.NetworkConstants
import com.jjg.testmvvm.model.network.set.NetworkStatus
import com.jjg.testmvvm.model.network.vo.resp.Document
import com.jjg.testmvvm.model.repository.NetworkRepository
import com.jjg.testmvvm.viewModel.common.BaseVm
import com.jjg.testmvvm.viewModel.vo.SearchResultVo


class SearchVm : BaseVm() {
    private val repository: NetworkRepository = NetworkRepository()

    val strSearch: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val resultSearch: LiveData<SearchResultVo> = Transformations.map(strSearch) {
        repository.search(it, statusNetwork)
    }

    val searchList: LiveData<PagedList<Document>> =
        Transformations.switchMap(resultSearch) { it -> it.data }

    private fun isEmpty(): Boolean {
        return strSearch.value!!.isEmpty()
    }

    private fun setStrSearch(str: String) {
        strSearch.value = str
    }

    fun clickSearch(str: String) {
        setStrSearch(str)
        if (isEmpty()) {
            resultSearch.value == null
            val url = NetworkConstants.BASE_URL + NetworkConstants.URL_SEARCH
            statusNetwork.value = NetworkStatus(url, STATUS.NONE)
            return
        }
    }

    fun isEmptyDocuments(): Boolean {
        return if (searchList.value == null || searchList.value!!.isEmpty()) {
            true
        } else {
            searchList.value == null
        }
    }
}