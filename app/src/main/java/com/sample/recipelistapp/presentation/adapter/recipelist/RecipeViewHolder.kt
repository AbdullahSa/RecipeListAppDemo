package com.sample.recipelistapp.presentation.adapter.recipelist

import androidx.recyclerview.widget.RecyclerView
import com.sample.recipelistapp.databinding.ItemRecipeListBinding

class RecipeViewHolder(
    val dataBinding: ItemRecipeListBinding,
    private val onItemClick: ((Int) -> Unit)? = null
) : RecyclerView.ViewHolder(dataBinding.root) {

    init {
        itemView.setOnClickListener {
            onItemClick?.invoke(adapterPosition)
        }
    }

    fun bindItem() {
        dataBinding.executePendingBindings()
    }
}