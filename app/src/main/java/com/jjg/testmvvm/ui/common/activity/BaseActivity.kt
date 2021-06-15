package com.jjg.testmvvm.ui.common.activity

import android.app.Activity
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.jjg.testmvvm.ui.dialog.LoadingDialog
import com.jjg.testmvvm.ui.dialog.NetworkDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * Mvvm패턴을 따르지 않는다면,
 * BaseActivity를 상속
 */
@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {

    @Inject lateinit var loadingDialog: LoadingDialog
    @Inject lateinit var networkDialog: NetworkDialog

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev!!.action == MotionEvent.ACTION_UP)
            hideKeyboard()
        return super.dispatchTouchEvent(ev)
    }


    private fun hideKeyboard() {
        val imm: InputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        val view: View = currentFocus ?: return
        imm.hideSoftInputFromWindow(view.windowToken, 0)
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