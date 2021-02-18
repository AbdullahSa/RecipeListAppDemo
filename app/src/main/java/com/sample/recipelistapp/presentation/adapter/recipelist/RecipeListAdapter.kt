package com.sample.recipelistapp.presentation.adapter.recipelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.sample.recipelistapp.R
import com.sample.recipelistapp.data.model.RecipeItem
import com.sample.recipelistapp.data.model.RecipePhoto
import com.sample.recipelistapp.data.model.RecipeWithPhotos
import com.sample.recipelistapp.databinding.ItemRecipeListBinding
import com.sample.recipelistapp.presentation.adapter.photolist.PhotoListAdapter

class RecipeListAdapter(
    private val onItemClicked: ((RecipeWithPhotos) -> Unit)? = null
) : ListAdapter<RecipeWithPhotos, RecipeViewHolder>(RecipeRecyclerItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding: ItemRecipeListBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_recipe_list,
                parent,
                false
            )
        return RecipeViewHolder(binding) {
            onItemClicked?.invoke(currentList[it])
        }
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val item = getItem(position)
        holder.dataBinding.data = item
        PhotoListAdapter().let {
            holder.dataBinding.rvImageList.adapter = it
            it.submitList(item.photoLists.map { photo ->
                photo.editable = false
                photo
            })
        }
        holder.bindItem()
    }
}