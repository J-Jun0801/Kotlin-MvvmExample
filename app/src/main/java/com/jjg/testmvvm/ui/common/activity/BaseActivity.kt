package com.jjg.testmvvm.ui.common.activity

import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.jjg.testmvvm.ui.dialog.LoadingDialog
import com.jjg.testmvvm.ui.dialog.NetworkDialog


/**
 * Mvvm패턴을 따르지 않는다면,
 * BaseActivity를 상속
 */
open class BaseActivity : AppCompatActivity() {

    private lateinit var loadingDialog: LoadingDialog
    private lateinit var networkDialog: NetworkDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        loadingDialog = LoadingDialog(this)
        networkDialog = NetworkDialog(this)
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

    fun showLoadingDialog() {
        if (!loadingDialog.isShowing) {
            loadingDialog = LoadingDialog(this)
            loadingDialog.show()
        }
    }


    fun closeLoadingDialog() {
        if (loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
    }

    fun showNetworkDialog(title: String, content: String) {
        if (!networkDialog.isShowing) {
            networkDialog = NetworkDialog(this)
            networkDialog.show()
            networkDialog.setNetworkDialog(title, content)
        }
    }

    fun closeNetworkDialog() {
        if (networkDialog.isShowing) {
            networkDialog.dismiss()
        }
    }
}