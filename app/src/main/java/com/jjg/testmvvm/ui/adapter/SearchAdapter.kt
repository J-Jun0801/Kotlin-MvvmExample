package com.jjg.testmvvm.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jjg.testmvvm.databinding.ItemSearchBinding
import com.jjg.testmvvm.ui.adapter.viewholder.SearchViewHolder
import com.jjg.testmvvm.viewModel.SearchVm


class SearchAdapter(
    private var context: Context,
    private var viewModel: SearchVm?
) : RecyclerView.Adapter<SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (viewModel == null)
            return 0
        return viewModel!!.voSearch.value!!.documents.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(viewModel!!.voSearch.value!!.documents[position])
    }

    fun setViewModel(viewModel: SearchVm) {
        this.viewModel = viewModel
    }
}