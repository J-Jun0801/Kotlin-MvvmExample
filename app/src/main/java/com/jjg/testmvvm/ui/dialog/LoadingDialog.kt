package com.jjg.testmvvm.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.jjg.testmvvm.R

class LoadingDialog(context: Context) : Dialog(context,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
        setCancelable(false)

    }
}