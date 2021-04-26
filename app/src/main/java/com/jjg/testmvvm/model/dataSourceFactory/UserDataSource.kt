package com.jjg.testmvvm.model.dataSourceFactory

import androidx.paging.PageKeyedDataSource
import com.jjg.testmvvm.model.network.core.ApiServiceBuilder
import com.jjg.testmvvm.model.network.set.IApiService
import com.jjg.testmvvm.model.network.set.NetworkConstants.Companion.FIRST_PAGE
import com.jjg.testmvvm.model.network.vo.resp.User
import com.jjg.testmvvm.model.network.vo.resp.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataSource() : PageKeyedDataSource<Int, User>() {
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {

        val service = ApiServiceBuilder.buildService(IApiService::class.java)
        val call = service.getUsers(params.key)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.users
                    val key = if (params.key > 1) params.key - 1 else 0
                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            }
        })

    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, User>
    ) {
        val service = ApiServiceBuilder.buildService(IApiService::class.java)
        val call = service.getUsers(FIRST_PAGE)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.users
                    responseItems?.let {
                        callback.onResult(responseItems, null, FIRST_PAGE + 1)
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        val service = ApiServiceBuilder.buildService(IApiService::class.java)
        val call = service.getUsers(params.key)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.users
                    val key = params.key + 1
                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            }
        })
    }
}
