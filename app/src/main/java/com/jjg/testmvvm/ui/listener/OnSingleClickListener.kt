package com.jjg.testmvvm.ui.listener

import android.os.SystemClock
import android.view.View


abstract class OnSingleClickListener : View.OnClickListener {
    //중복클릭시간차이
    private val minClickInterval: Long = 600

    //마지막으로 클릭한 시간
    private var lastClickTime: Long = 0

    abstract fun onSingleClick(v: View?)
    override fun onClick(v: View?) {
        //현재 클릭한 시간
        val currentClickTime = SystemClock.uptimeMillis()
        //이전에 클릭한 시간과 현재시간의 차이
        val elapsedTime = currentClickTime - lastClickTime
        //마지막클릭시간 업데이트
        lastClickTime = currentClickTime

        //내가 정한 중복클릭시간 차이를 안넘었으면 클릭이벤트 발생못하게 return
        if (elapsedTime <= minClickInterval) return
        //중복클릭시간 아니면 이벤트 발생

        onSingleClick(v)
    }
}