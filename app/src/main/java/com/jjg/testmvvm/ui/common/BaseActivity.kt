package com.jjg.testmvvm.ui.common

import android.app.Activity
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

/**
 * Mvvm패턴을 따르지 않는다면,
 * BaseActivity를 상속
 */
open class BaseActivity  : AppCompatActivity() {

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev!!.action == MotionEvent.ACTION_UP)
            hideKeyboard()
        return super.dispatchTouchEvent(ev)
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = currentFocus ?: return
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }
}