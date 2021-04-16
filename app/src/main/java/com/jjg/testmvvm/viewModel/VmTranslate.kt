package com.jjg.testmvvm.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jjg.testmvvm.model.network.NetworkRequest
import com.jjg.testmvvm.model.network.core.INetworkListener
import com.jjg.testmvvm.model.network.vo.resp.VoTranslate
import com.jjg.testmvvm.viewModel.common.VmBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VmTranslate : VmBase() {
    val voTranslate: MutableLiveData<VoTranslate> by lazy {
        MutableLiveData<VoTranslate>()
    }

    val strTranslate: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun translate(networkListener: INetworkListener) {
        networkListener.onPrepareListener()

        NetworkRequest.getInstance()
            .requestTranslate(strTranslate.value!!, object : Callback<VoTranslate> {
                override fun onFailure(call: Call<VoTranslate>, t: Throwable) {
                    Log.d("TAG", "========= fail ==============")
                    Log.d("TAG", "${t.message}")
                    Log.d("TAG", "=======================")
                    clearVoTranslate()
                    networkListener.onFailListener()
                }

                override fun onResponse(
                    call: Call<VoTranslate>,
                    response: Response<VoTranslate>
                ) {
                    Log.d("TAG", "===========success============")
                    Log.d("TAG", "${response.body()!!.translatedText}")
                    Log.d("TAG", "=======================")

                    if (response.isSuccessful) {
                        voTranslate.postValue(response.body())
                        networkListener.onSuccessListener()
                    } else {
                        clearVoTranslate()
                        networkListener.onFailListener()
                    }
                }
            })
    }

    private fun clearVoTranslate() {
        voTranslate.postValue(VoTranslate(listOf(listOf(""))))
    }

    fun isEmpty(): Boolean {
        return strTranslate.value!!.isEmpty()
    }

    fun setStrTranslate(str: String) {
        strTranslate.value = str
    }

}
