package com.saif.base.adapters

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<R, T : RecyclerView.ViewHolder>(protected var data: List<R> = ArrayList<R>()) :
    RecyclerView.Adapter<T>() {

    override fun getItemCount() = data.size

    fun submitList(data: List<R>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun submitListNew(newData: List<R>) {
        val oldSize = data.size
        data = data + newData
        notifyItemRangeInserted(oldSize, newData.size)
    }

}