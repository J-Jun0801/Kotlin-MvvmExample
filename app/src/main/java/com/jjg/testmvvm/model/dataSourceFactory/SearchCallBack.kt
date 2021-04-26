package com.jjg.testmvvm.model.dataSourceFactory

import androidx.paging.PagedList
import com.jjg.testmvvm.model.network.vo.resp.Document

class SearchCallBack : PagedList.BoundaryCallback<Document>() {

    override fun onItemAtEndLoaded(itemAtEnd: Document) {
        super.onItemAtEndLoaded(itemAtEnd)
    }
}