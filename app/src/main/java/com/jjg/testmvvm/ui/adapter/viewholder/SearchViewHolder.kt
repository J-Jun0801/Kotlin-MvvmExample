package com.jjg.testmvvm.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.jjg.testmvvm.databinding.ItemSearchBinding
import com.jjg.testmvvm.model.network.vo.resp.Document

class SearchViewHolder(private var binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(vo:Document){
        binding.vo = vo
    }
}