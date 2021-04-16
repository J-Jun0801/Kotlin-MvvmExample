package com.jjg.testmvvm.model.network.core

interface INetworkListener {
    fun onPrepareListener()
    fun onFailListener()
    fun onSuccessListener()
}