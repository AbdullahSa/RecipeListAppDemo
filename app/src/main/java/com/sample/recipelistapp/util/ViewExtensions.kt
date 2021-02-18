package com.sample.recipelistapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * to use it with binding view
 */
@BindingAdapter("app:srcPath")
fun ImageView.setPath(path: String?) {
    Glide.with(this).load(path)
        .into(this)
}