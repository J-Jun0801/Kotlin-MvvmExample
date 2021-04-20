package com.jjg.testmvvm.ui.common.application

import android.app.Application
import com.jjg.testmvvm.model.util.font.TypefaceUtil
import com.jjg.testmvvm.model.util.pref.Pref

/**
 * applicationContext는
 * 앱이 생성되고 앱이 죽을때까지 생성이 됨
 * Pref...와 같은 객체 생성
 */
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init(){
        Pref.getInstance(applicationContext)
    }
}