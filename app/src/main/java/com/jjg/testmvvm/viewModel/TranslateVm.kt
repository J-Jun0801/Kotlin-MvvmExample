package com.jjg.testmvvm.viewModel

import androidx.lifecycle.MutableLiveData
import com.jjg.testmvvm.model.network.core.RetrofitBuilder
import com.jjg.testmvvm.model.network.core.STATUS
import com.jjg.testmvvm.model.network.set.NetworkConstants
import com.jjg.testmvvm.model.network.set.NetworkStatus
import com.jjg.testmvvm.model.network.vo.resp.VoTranslate
import com.jjg.testmvvm.model.util.log.Log
import com.jjg.testmvvm.viewModel.common.BaseVm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TranslateVm : BaseVm() {
    val voTranslate: MutableLiveData<VoTranslate> by lazy {
        MutableLiveData<VoTranslate>()
    }

    val strTranslate: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }


    private fun translate(url: String) {
        statusNetwork.value = NetworkStatus( url, STATUS.PREPARED)
        val call = RetrofitBuilder().apiService.translate(
            strTranslate.value!!, NetworkConstants.LAGN_KR, NetworkConstants.LAGN_EN
        )
        call.enqueue( object : Callback<VoTranslate> {
                override fun onFailure(call: Call<VoTranslate>, t: Throwable) {
                    Log.d( "========= fail ==============")
                    Log.d( "${t.message}")
                    Log.d( "=======================")
                    clearVoTranslate(url,"",t.message.toString())
                }

                override fun onResponse(
                    call: Call<VoTranslate>,
                    response: Response<VoTranslate>
                ) {
                    Log.d( "===========success============")
                    Log.d( "${response.body()!!.translatedText}")
                    Log.d( "=======================")
                    if (response.isSuccessful) {
                        voTranslate.postValue(response.body())
                        statusNetwork.value = NetworkStatus( url, STATUS.SUCCESS)
                    } else {
                        clearVoTranslate(url,response.code().toString(), response.message())
                    }
                }
            })
    }

    private fun clearVoTranslate(url: String, title: String, message: String) {
        voTranslate.postValue(VoTranslate(listOf(listOf(""))))
        statusNetwork.value = NetworkStatus( url, STATUS.FAIL,title,message)
    }

    private fun isEmpty(): Boolean {
        return strTranslate.value!!.isEmpty()
    }

    private fun setStrTranslate(str: String) {
        strTranslate.value = str
    }

    fun clickTranslate(str: String) {
        val url = NetworkConstants.BASE_URL + NetworkConstants.URL_SEARCH
        statusNetwork.value = NetworkStatus( url, STATUS.NONE)
        setStrTranslate(str)
        if (isEmpty()) {
            return
        }
        translate(url)
    }

}
