package com.rocksolidapps.cake.ui.cakes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rocksolidapps.cake.R
import com.rocksolidapps.cake.api.model.Cake
import com.rocksolidapps.cake.databinding.ItemCakeBinding

class CakeAdapter : ListAdapter<Cake, CakeAdapter.CakeViewHolder>(CakeDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CakeViewHolder {
        val binding: ItemCakeBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_cake, parent, false)
        return CakeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CakeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CakeViewHolder(private val binding: ItemCakeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Cake) {
            with(binding) {
                cake = item
                executePendingBindings()
            }
        }
    }
}

class CakeDiffCallback : DiffUtil.ItemCallback<Cake>() {
    override fun areItemsTheSame(oldItem: Cake, newItem: Cake): Boolean = oldItem.title == newItem.title // TODO: the best check is by ID but is not present
    override fun areContentsTheSame(oldItem: Cake, newItem: Cake): Boolean = if (oldItem.desc != newItem.desc) false else oldItem.image == newItem.image
}