package com.jjg.testmvvm.model.network

import com.jjg.testmvvm.model.network.core.RetrofitBuilder
import com.jjg.testmvvm.model.network.set.NetworkConstants
import com.jjg.testmvvm.model.network.vo.resp.VoSearch
import com.jjg.testmvvm.model.network.vo.resp.VoTranslate
import retrofit2.Callback

/**
 * 실질적인 Network Model
 * ViewModel에서는 NetwrkRequest.class만
 * 바라보고있으면 된다
 */
class NetworkRequest {
    companion object {
        private var instance: NetworkRequest? = null
        fun getInstance(): NetworkRequest {
            if (instance == null) {
                synchronized(NetworkRequest::class.java) {
                    instance = NetworkRequest()
                }
            }
            return instance!!
        }
    }

    fun requestTranslate(query: String, listener: Callback<VoTranslate>) {
        val call = RetrofitBuilder().apiService.translate(
            query, NetworkConstants.LAGN_KR, NetworkConstants.LAGN_EN
        )
        call.enqueue(listener)
    }

    fun requestSearch(query: String, listener: Callback<VoSearch>) {
        val call = RetrofitBuilder().apiService.search(query)
        call.enqueue(listener)
    }
}