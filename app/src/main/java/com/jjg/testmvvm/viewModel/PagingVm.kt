package com.jjg.testmvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jjg.testmvvm.model.dataSourceFactory.UserDataSource
import com.jjg.testmvvm.model.dataSourceFactory.UserDataSourceFactory
import com.jjg.testmvvm.model.network.set.NetworkConstants.Companion.PAGE_SIZE
import com.jjg.testmvvm.model.network.vo.resp.User
import com.jjg.testmvvm.viewModel.common.BaseVm

class PagingVm : BaseVm() {
    var userPagedList: LiveData<PagedList<User>>
    private var liveDataSource: LiveData<UserDataSource>
    init {
        val itemDataSourceFactory = UserDataSourceFactory()
        liveDataSource = itemDataSourceFactory.userLiveDataSource
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PAGE_SIZE)
            .build()
        userPagedList = LivePagedListBuilder(itemDataSourceFactory, config)
            .build()
    }
}