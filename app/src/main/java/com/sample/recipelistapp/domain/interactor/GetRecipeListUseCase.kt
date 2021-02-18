package com.sample.recipelistapp.domain.interactor

import com.sample.recipelistapp.domain.RecipeListRepository

class GetRecipeListUseCase constructor(private val repository: RecipeListRepository) {
    suspend fun getRecipeList() = repository.getAllRecipeList()
}