package com.jjg.testmvvm.data.dataSourceFactory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.jjg.testmvvm.model.network.vo.resp.Document

/**
 * https://developer.android.com/reference/android/arch/paging/DataSource.Factory
 * DataSource 생성 class
 *
 * @author jjg 21.05.03
 */
class SearchDataFactory  (
    private val query: String
) : DataSource.Factory<Int, Document>() {

    private val dataSourceLiveData: MutableLiveData<SearchDataSource> = MutableLiveData<SearchDataSource>()
    private var dataSource: SearchDataSource? = null

    override fun create(): DataSource<Int, Document> {
        dataSource = SearchDataSource(query)
        dataSourceLiveData.postValue(dataSource)
        return dataSource!!
    }

}