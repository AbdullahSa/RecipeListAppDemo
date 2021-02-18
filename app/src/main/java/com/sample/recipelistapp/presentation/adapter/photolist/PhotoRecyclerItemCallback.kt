package com.sample.recipelistapp.presentation.adapter.photolist

import androidx.recyclerview.widget.DiffUtil
import com.sample.recipelistapp.data.model.RecipePhoto

class PhotoRecyclerItemCallback : DiffUtil.ItemCallback<RecipePhoto>() {

    override fun areItemsTheSame(oldItem: RecipePhoto, newItem: RecipePhoto) = oldItem == newItem

    override fun areContentsTheSame(oldItem: RecipePhoto, newItem: RecipePhoto) =
        oldItem.photoId == newItem.photoId && oldItem.photoPath == newItem.photoPath
}