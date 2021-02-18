package com.sample.recipelistapp.domain.interactor

import com.sample.recipelistapp.data.model.RecipeWithPhotos
import com.sample.recipelistapp.domain.RecipeListRepository

class SaveNewRecipeUseCase constructor(private val repository: RecipeListRepository) {
    suspend fun saveNewRecipe(recipeItem: RecipeWithPhotos) = repository.saveNewRecipe(recipeItem)
}