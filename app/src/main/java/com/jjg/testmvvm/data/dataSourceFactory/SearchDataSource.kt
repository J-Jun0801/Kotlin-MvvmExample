package com.jjg.testmvvm.data.dataSourceFactory

import androidx.paging.PageKeyedDataSource
import com.jjg.testmvvm.model.network.NetworkRequest
import com.jjg.testmvvm.model.network.vo.resp.Document
import com.jjg.testmvvm.model.util.log.Log

/**
 * https://developer.android.com/reference/android/arch/paging/DataSource
 * 데이터 SNAPSHOTS을 PagedList에 로드 해주는 class
 *
 * @author jjg 21.05.13
 */
class SearchDataSource(private val query: String) : PageKeyedDataSource<Int, Document>() {

    override fun loadInitial(
        params: PageKeyedDataSource.LoadInitialParams<Int>,
        callback: PageKeyedDataSource.LoadInitialCallback<Int, Document>
    ) {
        Log.i("================ loadInitial, size : ${params.requestedLoadSize}")
        val curPage = 1
        val nextPage = curPage + 1

        NetworkRequest.getInstance().search(query, curPage, 10,
            { repos ->
                callback.onResult(repos, null, nextPage)
            }, { error ->
                //FIXME 에러일경우 어떻게 해야할까 ..
            })
    }

    override fun loadAfter(
        params: PageKeyedDataSource.LoadParams<Int>,
        callback: PageKeyedDataSource.LoadCallback<Int, Document>
    ) {
        Log.i("================ loadAfter, key: ${params.key}, size: ${params.requestedLoadSize}")
        NetworkRequest.getInstance().search(query, params.key, 10,
            { repos ->
                val nextKey = params.key + 1
                callback.onResult(repos, nextKey)
            }, { error ->
                //FIXME 에러일경우 어떻게 해야할까 ..
            }
        )
    }

    override fun loadBefore(
        params: PageKeyedDataSource.LoadParams<Int>,
        callback: PageKeyedDataSource.LoadCallback<Int, Document>
    ) {
    }
}
