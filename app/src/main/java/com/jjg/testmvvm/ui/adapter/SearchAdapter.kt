package com.jjg.testmvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.jjg.testmvvm.databinding.ItemSearchBinding
import com.jjg.testmvvm.model.network.vo.resp.Document
import com.jjg.testmvvm.ui.adapter.viewholder.SearchViewHolder

/**
 * https://brunch.co.kr/@oemilk/211
 */

class SearchAdapter() : PagedListAdapter<Document, SearchViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val item = getItem(position)!!
        holder.bind(item)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Document>() {
            /**
             * 고유 값을 비교하는게 좋다.
             */
            override fun areItemsTheSame(oldItem: Document, newItem: Document): Boolean {
                return oldItem == newItem
            }

            /**
             * 아이템을 서로 비교하는게 좋다.
             */
            override fun areContentsTheSame(oldItem: Document, newItem: Document): Boolean {
                return oldItem == newItem;
            }
        }
    }
}