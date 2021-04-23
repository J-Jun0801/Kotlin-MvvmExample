package com.jjg.testmvvm.viewModel

import androidx.lifecycle.MutableLiveData
import com.jjg.testmvvm.viewModel.common.BaseVm

class MenuVm : BaseVm() {
    companion object {
        const val EVENT_ID_TRANSLATE = 0
        const val EVENT_ID_SEARCH = 1
    }

    val eventId: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    fun clickTranslate() {
        eventId.value = EVENT_ID_TRANSLATE
    }

    fun clickSearch() {
        eventId.value = EVENT_ID_SEARCH
    }
}