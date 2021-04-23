package com.jjg.testmvvm.model.dataSourceFactory

import androidx.paging.PositionalDataSource
import com.jjg.testmvvm.model.network.vo.resp.Document

//(private val list: ArrayList<Document>)
class SearchPositionalDataSource() : PositionalDataSource<Document>() {
    private fun computeCount(): Int {
        // 실제 데이터의 사이즈를 반환
//        return list.size
        return 5
    }

    private fun fetchItem(startPosition: Int, loadCount: Int): ArrayList<Document> {
        // 특정 포지션으로부터 원하는 만큼의 데이터를 이곳에서 로드
        val modelList = ArrayList<Document>()
//        val endPosition = Math.min(computeCount(), startPosition + loadCount)
//        for (i in startPosition until endPosition) {
//            modelList.add(list[i])
//        }
        return modelList
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Document>) {

        val totalCount = computeCount()
        val position = computeInitialLoadPosition(params, totalCount)
        val loadSize = computeInitialLoadSize(params, position, totalCount)
        callback.onResult(fetchItem(position, loadSize), position, totalCount)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Document>) {
        callback.onResult(fetchItem(params.startPosition, params.loadSize))
    }
}