package com.jjg.testmvvm.model.dataSourceFactory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.jjg.testmvvm.model.network.vo.resp.User


class UserDataSourceFactory : DataSource.Factory<Int, User>() {
    val userLiveDataSource = MutableLiveData<UserDataSource>()
    override fun create(): DataSource<Int, User> {
        val userDataSource = UserDataSource()
        userLiveDataSource.postValue(userDataSource)
        return userDataSource
    }
}
