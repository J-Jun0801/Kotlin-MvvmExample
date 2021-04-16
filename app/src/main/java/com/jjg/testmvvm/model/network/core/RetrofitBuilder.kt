package com.jjg.testmvvm.model.network.core

import com.jjg.testmvvm.model.network.set.IApiSerivce
import com.jjg.testmvvm.model.network.set.NetworkConstants.Companion.BASE_URL
import com.jjg.testmvvm.model.network.set.NetworkConstants.Companion.H_KEY_AUTHORIZATION
import com.jjg.testmvvm.model.network.set.NetworkConstants.Companion.H_VALUE_KAKAO_AK
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    val apiService: IApiSerivce

    init {
        val headerInterceptor = Interceptor {
            val request = it.request()
                .newBuilder()
                .addHeader(H_KEY_AUTHORIZATION, H_VALUE_KAKAO_AK)
                .build()
            return@Interceptor it.proceed(request)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .build()

        apiService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IApiSerivce::class.java)
    }
}