package com.jjg.testmvvm.viewModel

import androidx.lifecycle.MutableLiveData
import com.jjg.testmvvm.model.network.NetworkRequest
import com.jjg.testmvvm.model.network.core.STATUS
import com.jjg.testmvvm.model.network.set.NetworkConstants
import com.jjg.testmvvm.model.network.set.NetworkStatus
import com.jjg.testmvvm.model.network.vo.resp.VoTranslate
import com.jjg.testmvvm.model.util.log.Log
import com.jjg.testmvvm.viewModel.common.BaseVm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *
 * @author
 */
class TranslateVm : BaseVm() {
    val voTranslate: MutableLiveData<VoTranslate> by lazy {
        MutableLiveData<VoTranslate>()
    }

    val strTranslate: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    /**
     *
     * @param url
     * @return
     */
    private fun translate(url: String) {

        statusNetwork.value = NetworkStatus( url, STATUS.PREPARED)
        NetworkRequest.getInstance()
            .requestTranslate(strTranslate.value!!, object : Callback<VoTranslate> {
                override fun onFailure(call: Call<VoTranslate>, t: Throwable) {
                    Log.d( "========= fail ==============")
                    Log.d( "${t.message}")
                    Log.d( "=======================")
                    clearVoTranslate(url)
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
                        clearVoTranslate(url)
                    }
                }
            })
    }

    private fun clearVoTranslate(url: String) {
        voTranslate.postValue(VoTranslate(listOf(listOf(""))))
        statusNetwork.value = NetworkStatus( url, STATUS.FAIL)
    }

    private fun isEmpty(): Boolean {
        return strTranslate.value!!.isEmpty()
    }

    private fun setStrTranslate(str: String) {
        strTranslate.value = str
    }

    fun clickTranslate(str: String) {
        var url = NetworkConstants.BASE_URL + NetworkConstants.URL_SEARCH
        statusNetwork.value = NetworkStatus( url, STATUS.NONE)
        setStrTranslate(str)
        if (isEmpty()) {
            return
        }
        translate(url)
    }

}
