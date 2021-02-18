package com.sample.recipelistapp.domain

import com.sample.recipelistapp.data.model.RecipeWithPhotos
import kotlinx.coroutines.flow.Flow

interface RecipeListRepository {
    /**
     * Get All Recipe List From Local DB
     */
    suspend fun getAllRecipeList(): Flow<List<RecipeWithPhotos>>

    /**
     * Save New Recipe Data into Local DB
     */
    suspend fun saveNewRecipe(mergedRecipeModel: RecipeWithPhotos): Boolean
}