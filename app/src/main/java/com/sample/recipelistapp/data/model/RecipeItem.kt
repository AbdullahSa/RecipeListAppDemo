package com.sample.recipelistapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * RecipeItem recipe item model for recipe list
 */
@Entity(tableName = "recipe")
data class RecipeItem(
    @PrimaryKey(autoGenerate = true)
    val recipeId: Long = 0,
    val title: String,
    val description: String
)