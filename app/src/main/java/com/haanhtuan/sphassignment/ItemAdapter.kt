package com.haanhtuan.sphassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haanhtuan.sphassignment.data.model.Year
import com.haanhtuan.sphassignment.databinding.ItemLayoutBinding
import timber.log.Timber

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private var items: List<Year> = emptyList()

    companion object {
        @JvmStatic
        @BindingAdapter("items")
        fun RecyclerView.bindItems(items: List<Year>?) {
            Timber.e("tuan: "+ items)
            if (items != null) {
                Timber.e("tuan: "+ items.size)
                val adapter = adapter as ItemAdapter
                adapter.update(items)
            }
        }
    }

    fun update(items: List<Year>) {
            this.items = items
            notifyDataSetChanged()
        }

    class ItemViewHolder(
        private val parent: ViewGroup,
        private val binding: ItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_layout,
            parent,
            false
        )
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(year: Year) {
            binding.item = year

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        if (items.size > position) {
            holder.bind(items[position])
        }
    }

    override fun getItemCount() = items.size
}