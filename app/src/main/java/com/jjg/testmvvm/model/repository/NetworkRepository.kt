/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jjg.testmvvm.model.repository

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jjg.testmvvm.data.dataSourceFactory.SearchDataFactory
import com.jjg.testmvvm.viewModel.vo.SearchResultVo
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class NetworkRepository() {
    private val executor: Executor = Executors.newFixedThreadPool(5)

    fun search(query: String): SearchResultVo {
        val dataSourceFactory = SearchDataFactory(query)

        val pagedListConfig = PagedList.Config.Builder()
            .setPageSize(10)
            .setInitialLoadSizeHint(10)
            .setPrefetchDistance(10)
            .setEnablePlaceholders(true)
            .build()

        val data = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
            .setFetchExecutor(executor)
            .build()

//        val networkErrors = Transformations.switchMap(dataSourceFactory.mutableLiveData,
//            { dataSource ->
//                dataSource.networkErrors
//            }
//        )

        return SearchResultVo(data)

    }

}