package com.jjg.testmvvm.data.dataSourceFactory

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.jjg.testmvvm.model.network.core.RetrofitBuilder
import com.jjg.testmvvm.model.network.core.STATUS
import com.jjg.testmvvm.model.network.set.NetworkConstants
import com.jjg.testmvvm.model.network.set.NetworkConstants.Companion.PAGE_SIZE
import com.jjg.testmvvm.model.network.set.NetworkStatus
import com.jjg.testmvvm.model.network.vo.resp.Document
import com.jjg.testmvvm.model.network.vo.resp.VoSearch
import com.jjg.testmvvm.model.util.log.Log
import retrofit2.Call
import retrofit2.Response

/**
 * https://developer.android.com/reference/android/arch/paging/DataSource
 * 데이터 SNAPSHOTS을 PagedList에 로드 해주는 class
 *
 * @author jjg 21.05.13
 */
class SearchDataSource(
    private val query: String,
    private var statusNetwork: MutableLiveData<NetworkStatus>
) : PageKeyedDataSource<Int, Document>() {

    /**
     * 최초 진입시 로드
     */
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Document>
    ) {
        if (query.isEmpty())
            return

        Log.i("================ loadInitial, size : ${params.requestedLoadSize}")
        val curPage = 1
        val nextPage = curPage + 1
        requestSearch(curPage, PAGE_SIZE) { repos ->
            callback.onResult(repos, null, nextPage)
        }
    }

    /**
     * snap shot 이후 불러올 데이터
     */
    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Document>
    ) {
        Log.i("================ loadAfter, key: ${params.key}, size: ${params.requestedLoadSize}")
        requestSearch(params.key, PAGE_SIZE) { repos ->
            val nextKey = params.key + 1
            callback.onResult(repos, nextKey)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Document>
    ) {
    }

    private fun requestSearch(
        page: Int,
        size: Int,
        onSuccess: (repos: List<Document>) -> Unit
    ) {
        val url = NetworkConstants.BASE_URL + NetworkConstants.URL_SEARCH
        statusNetwork.postValue(NetworkStatus(url, STATUS.PREPARED))

        val call = RetrofitBuilder().apiService.search(query, page.toString(), size.toString())
        call.enqueue(object : retrofit2.Callback<VoSearch> {
            override fun onFailure(call: Call<VoSearch>?, t: Throwable) {
                Log.d("fail to get data")

                statusNetwork.postValue(NetworkStatus(url, STATUS.FAIL, "", t.message.toString()))
            }

            override fun onResponse(
                call: Call<VoSearch>?,
                response: Response<VoSearch>
            ) {
                Log.d("got a response $response")
                if (response.isSuccessful) {
                    val repos: ArrayList<Document> = response.body()!!.documents
                    onSuccess(repos)
                    statusNetwork.postValue(NetworkStatus(url, STATUS.SUCCESS))
                } else {
                    statusNetwork.postValue(
                        NetworkStatus(
                            url,
                            STATUS.FAIL,
                            response.code().toString(),
                            response.message()
                        )
                    )
                }
            }
        })
    }
}


