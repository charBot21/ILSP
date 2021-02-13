package com.carlostorres.ilsp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.carlostorres.ilsp.R

class ItemsAdapter(val items: List<Int>): RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemsViewHolder( inflater.inflate(R.layout.items_rv, parent, false) )
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.initialize(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemsViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvTitle = view.findViewById<TextView>(R.id.tvNumber)

        fun initialize(item: Int) {
            tvTitle.text = item.toString()
        }
    }
}