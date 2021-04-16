package com.jjg.testmvvm.ui.common

import android.app.Application

/**
 * applicationContext는
 * 앱이 생성되고 앱이 죽을때까지 생성이 됨
 * Pref...와 같은 객체 생성
 */
class BaseApplication : Application() {
}