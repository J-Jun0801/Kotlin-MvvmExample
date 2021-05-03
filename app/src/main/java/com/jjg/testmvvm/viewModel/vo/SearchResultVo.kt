package com.jjg.testmvvm.viewModel.vo

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.jjg.testmvvm.model.network.vo.resp.Document

class SearchResultVo(
    val data: LiveData<PagedList<Document>>
)
