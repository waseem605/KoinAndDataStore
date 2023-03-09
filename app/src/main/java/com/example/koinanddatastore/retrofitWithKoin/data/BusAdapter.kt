package com.example.koinanddatastore.retrofitWithKoin.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.koinanddatastore.databinding.EachRowBinding
import com.example.koinanddatastore.retrofitWithKoin.data.utils.Listener


class BusAdapter constructor(private val listener: Listener) :
    ListAdapter<Post, BusAdapter.BusViewHolder>(Diff) {

    inner class BusViewHolder(private val binding: EachRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.apply {
                busNo.text = post.title
                towns.text = post.body
                delete.setOnClickListener {
                    listener.onClickDelete(adapterPosition, post.id.toString())
                }
            }
        }
    }

    object Diff : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusViewHolder {
        return BusViewHolder(
            EachRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BusViewHolder, position: Int) {
        val bus = getItem(position)
        if (bus != null) {
            holder.bind(bus)
        }
    }
}