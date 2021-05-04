package com.jjg.testmvvm.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.jjg.testmvvm.R
import kotlinx.android.synthetic.main.dialog_network.*

class NetworkDialog(context: Context) :
    Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_network)
        setCancelable(false)

        btn_confirm.setOnClickListener {
            dismiss()
        }
    }

    fun setNetworkDialog(title: String, content: String) {
        tv_content.text = title+"\n"+content
    }
}