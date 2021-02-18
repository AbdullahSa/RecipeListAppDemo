package com.sample.recipelistapp.presentation.adapter.recipelist

import androidx.recyclerview.widget.DiffUtil
import com.sample.recipelistapp.data.model.RecipeItem
import com.sample.recipelistapp.data.model.RecipePhoto
import com.sample.recipelistapp.data.model.RecipeWithPhotos

class RecipeRecyclerItemCallback : DiffUtil.ItemCallback<RecipeWithPhotos>() {

    override fun areItemsTheSame(oldItem: RecipeWithPhotos, newItem: RecipeWithPhotos) =
        oldItem == newItem

    override fun areContentsTheSame(
        oldItem: RecipeWithPhotos,
        newItem: RecipeWithPhotos
    ) =
        oldItem.recipeItem == newItem.recipeItem &&
                oldItem.photoLists == newItem.photoLists
}