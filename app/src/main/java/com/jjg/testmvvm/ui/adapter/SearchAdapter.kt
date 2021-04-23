package com.jjg.testmvvm.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.jjg.testmvvm.databinding.ItemSearchBinding
import com.jjg.testmvvm.model.network.vo.resp.Document
import com.jjg.testmvvm.ui.adapter.viewholder.SearchViewHolder
import com.jjg.testmvvm.viewModel.SearchVm

/**
 * https://brunch.co.kr/@oemilk/211
 */

class SearchAdapter(    private var context: Context,    private var viewModel: SearchVm?)
    : PagedListAdapter<Document,SearchViewHolder>(DIFF_CALLBACK) {
//    : RecyclerView.Adapter<SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        if (viewModel == null)
            return 0
        return viewModel!!.voSearch.value!!.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(viewModel!!.voSearch.value!![position])
    }

    fun setViewModel(viewModel: SearchVm) {
        this.viewModel = viewModel
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Document>() {
            override fun areItemsTheSame(oldItem: Document, newItem: Document): Boolean {
                TODO("Not yet implemented")
            }

            override fun areContentsTheSame(oldItem: Document, newItem: Document): Boolean {
                TODO("Not yet implemented")
            }

        }
    }
}