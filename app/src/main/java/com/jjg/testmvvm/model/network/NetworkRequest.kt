package com.jjg.testmvvm.model.network

import com.jjg.testmvvm.model.network.core.RetrofitBuilder
import com.jjg.testmvvm.model.network.set.NetworkConstants
import com.jjg.testmvvm.model.network.vo.resp.Document
import com.jjg.testmvvm.model.network.vo.resp.VoSearch
import com.jjg.testmvvm.model.network.vo.resp.VoTranslate
import com.jjg.testmvvm.model.util.log.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    fun search(
        query: String, page: Int,size:Int,
        onSuccess: (repos: List<Document>) -> Unit,
        onError: (error: String) -> Unit
    ) {
        val call = RetrofitBuilder().apiService.search(query, page.toString(),size.toString())
        call.enqueue(object : Callback<VoSearch> {
            override fun onFailure(call: Call<VoSearch>?, t: Throwable) {
                Log.d("fail to get data")
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(
                call: Call<VoSearch>?,
                response: Response<VoSearch>
            ) {
                Log.d("got a response $response")
                if (response.isSuccessful) {
                    val repos:ArrayList<Document> = response.body()!!.documents
                    onSuccess(repos)
                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
        })
    }
}