package com.sample.recipelistapp.presentation.adapter.photolist

import androidx.recyclerview.widget.RecyclerView
import com.sample.recipelistapp.databinding.ItemPhotoListBinding

class PhotoViewHolder(
    val dataBinding: ItemPhotoListBinding,
    private val onItemClick: ((Int) -> Unit)? = null,
    private val onRemoveClick: ((Int) -> Unit)? = null
) : RecyclerView.ViewHolder(dataBinding.root) {

    init {
        itemView.setOnClickListener {
            onItemClick?.invoke(adapterPosition)
        }
        dataBinding.ivDelete.setOnClickListener {
            onRemoveClick?.invoke(adapterPosition)
        }
    }

    fun bindItem() {
        dataBinding.executePendingBindings()
    }
}