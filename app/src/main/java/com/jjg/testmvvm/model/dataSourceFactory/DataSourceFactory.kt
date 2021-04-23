package com.jjg.testmvvm.model.dataSourceFactory

import androidx.paging.DataSource
import com.jjg.testmvvm.model.network.vo.resp.Document


class DataSourceFactory : DataSource.Factory<Int, Document>() {
    override fun create(): DataSource<Int, Document> {
        return SearchPositionalDataSource( )
    }
}
