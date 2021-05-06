package com.jjg.testmvvm.ui.adapter.viewholder

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jjg.testmvvm.databinding.ItemSearchBinding
import com.jjg.testmvvm.model.network.vo.resp.Document
import com.jjg.testmvvm.ui.listener.OnSingleClickListener

class SearchViewHolder(private var binding: ItemSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(vo: Document) {
        binding.vo = vo
        binding.rlItem.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View?) {
                moveUrl(v!!, vo.url)
            }
        })
    }

    private fun moveUrl(v: View, url: String) {
        var intent = Intent(ACTION_VIEW, Uri.parse(url))
        v.context.startActivity(intent)
    }
}