package com.sample.recipelistapp.data.model

import androidx.room.Embedded
import androidx.room.Relation

/**
 * For relationship of tables - one to many
 */
data class RecipeWithPhotos(
    @Embedded val recipeItem: RecipeItem,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "recipeOwnerId"
    )
    val photoLists: List<RecipePhoto>
)