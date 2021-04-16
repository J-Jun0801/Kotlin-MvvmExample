package com.jjg.testmvvm.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.jjg.testmvvm.R
import com.jjg.testmvvm.databinding.ActivityTranslateBinding
import com.jjg.testmvvm.model.network.core.INetworkListener
import com.jjg.testmvvm.ui.common.BaseMvvmActivity
import com.jjg.testmvvm.viewModel.VmTranslate

class TranslateActivity : BaseMvvmActivity<ActivityTranslateBinding, VmTranslate>(
    R.layout.activity_translate, VmTranslate::class.java
) {
    private val TAG = javaClass.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initValue()
    }

    override fun bindViewModel() {
        binding.viewModel = viewModel
    }

    private fun initView() {
        binding.btnConfirm.setOnClickListener {
            viewModel.setStrTranslate(binding.etTranslate.text.toString())
            if (viewModel.isEmpty()) {
                Toast.makeText(
                    this,
                    getString(R.string.str_hint_translate_input),
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            requestTranslate()
        }
    }

    private fun initValue() {
        viewModel.setStrTranslate(getString(R.string.str_retry))
    }

    private fun requestTranslate() {
        viewModel.translate(object : INetworkListener {
            override fun onPrepareListener() {
                Log.d(TAG, "OnPrepareListener")
            }

            override fun onFailListener() {
                Log.d(TAG, "OnFailListener")
            }

            override fun onSuccessListener() {
                Log.d(TAG, "OnSuccessListener")
            }
        })
    }


}