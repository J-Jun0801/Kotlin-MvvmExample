package com.jjg.testmvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jjg.testmvvm.databinding.ItemSearchBinding
import com.jjg.testmvvm.ui.adapter.viewholder.SearchViewHolder
import com.jjg.testmvvm.viewModel.SearchVm

/**
 * https://brunch.co.kr/@oemilk/211
 */

class SearchAdapter(private var viewModel: SearchVm?)
//    : PagedListAdapter<Document,SearchViewHolder>(DIFF_CALLBACK) {
    : RecyclerView.Adapter<SearchViewHolder>() {

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
//
//    companion object{
//        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Document>() {
//            /**
//             * 고유 값을 비교하는게 좋다.
//             */
//            override fun areItemsTheSame(oldItem: Document, newItem: Document): Boolean {
//                return oldItem.url == newItem.url
//            }
//            /**
//             * 아이템을 서로 비교하는게 좋다.
//             */
//            override fun areContentsTheSame(oldItem: Document, newItem: Document): Boolean {
//                return oldItem == newItem;
//            }
//
//        }
//    }
}