package com.jjg.testmvvm.model.network.core

interface INetworkListener  {
    fun onNoneListener ()
    fun onPrepareListener ()
    fun onFailListener ()
    fun onSuccessListener(url: String)
}