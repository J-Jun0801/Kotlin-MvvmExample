package com.jjg.testmvvm.model.network.set

import com.jjg.testmvvm.model.network.core.STATUS

class NetworkStatus {
    var url: String = ""

    var status: STATUS

    var errorTitle: String = ""

    var errorContent: String = ""


    constructor(url: String, status: STATUS, errorTitle: String, errorContent: String) {
        this.url = url
        this.status = status
        this.errorTitle = errorTitle
        this.errorContent = errorContent
    }

    constructor(url: String, status: STATUS) {
        this.url = url
        this.status = status
    }


}