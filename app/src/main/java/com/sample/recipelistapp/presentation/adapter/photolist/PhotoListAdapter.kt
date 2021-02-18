package com.sample.recipelistapp.presentation.adapter.photolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.sample.recipelistapp.R
import com.sample.recipelistapp.data.model.RecipePhoto
import com.sample.recipelistapp.databinding.ItemPhotoListBinding

class PhotoListAdapter(
    private val onItemClicked: ((RecipePhoto) -> Unit)? = null,
    private val onRemoveItemClicked: ((RecipePhoto) -> Unit)? = null
) : ListAdapter<RecipePhoto, PhotoViewHolder>(PhotoRecyclerItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding: ItemPhotoListBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_photo_list,
                parent,
                false
            )
        return PhotoViewHolder(binding, {
            currentList[it].takeIf { item -> item.photoPath == null }?.let { item ->
                onItemClicked?.invoke(item)
            }
        }) {
            onRemoveItemClicked?.invoke(currentList[it])
        }
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = getItem(position)
        holder.dataBinding.ivPlus.visibility =
            if (item.editable == true && item.photoPath == null) View.VISIBLE else View.GONE
        holder.dataBinding.ivDelete.visibility =
            if (item.editable == true && item.photoPath != null) View.VISIBLE else View.GONE
        holder.dataBinding.data = item
        holder.bindItem()
    }
}