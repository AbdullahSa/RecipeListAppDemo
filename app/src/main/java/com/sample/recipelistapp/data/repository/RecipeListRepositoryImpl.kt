package com.sample.recipelistapp.data.repository

import com.sample.recipelistapp.data.datasource.local.RecipeDao
import com.sample.recipelistapp.data.model.RecipeItem
import com.sample.recipelistapp.data.model.RecipePhoto
import com.sample.recipelistapp.data.model.RecipeWithPhotos
import com.sample.recipelistapp.domain.RecipeListRepository
import javax.inject.Inject

class RecipeListRepositoryImpl constructor(
    private val recipeDao: RecipeDao
) : RecipeListRepository {

    override suspend fun getAllRecipeList() = recipeDao.getAllRecipeList()

    override suspend fun saveNewRecipe(mergedRecipeModel: RecipeWithPhotos) = run {
        val id = recipeDao.addNewRecipeEntity(mergedRecipeModel.recipeItem)
        recipeDao.addPhotoEntities(mergedRecipeModel.photoLists.map {
            it.recipeOwnerId = id
            it
        })
        true
    }

}