package com.jjg.testmvvm.model.network.core

interface INetworkListener {
    fun onNoneListener()
    fun onPrepareListener()
    fun onFailListener( title:String,  message:String)
    fun onSuccessListener(url: String)
}