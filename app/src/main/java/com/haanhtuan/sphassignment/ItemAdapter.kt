package com.haanhtuan.sphassignment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.haanhtuan.sphassignment.data.model.Quarter
import com.haanhtuan.sphassignment.data.model.Year
import com.haanhtuan.sphassignment.databinding.ItemLayoutBinding
import timber.log.Timber

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private var items: List<Year> = emptyList()

    companion object {
        @JvmStatic
        @BindingAdapter("items")
        fun RecyclerView.bindItems(items: List<Year>?) {
            val adapter = adapter as ItemAdapter
            if (items != null) {
                adapter.update(items)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
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
            //click to imageView
            binding.imageView.setOnClickListener {
                year.isShowQuartersDetail = !year.isShowQuartersDetail
                if (year.isShowQuartersDetail) binding.constraintUsageDetail.visibility =
                    View.VISIBLE
                else binding.constraintUsageDetail.visibility = View.GONE
            }

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