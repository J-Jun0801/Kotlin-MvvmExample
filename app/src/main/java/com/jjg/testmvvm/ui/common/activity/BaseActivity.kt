package com.jjg.testmvvm.ui.common.activity

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.jjg.testmvvm.ui.dialog.CustomsDialog


/**
 * Mvvm패턴을 따르지 않는다면,
 * BaseActivity를 상속
 */
open class BaseActivity : AppCompatActivity() {

    private lateinit var dialog: CustomsDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init(){
        dialog=CustomsDialog(this)
    }
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

    fun showDialog() {
        if (!dialog.isShowing) {
            dialog = CustomsDialog(this)
            dialog.show()
        }
    }


    fun closeDialog() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }
}