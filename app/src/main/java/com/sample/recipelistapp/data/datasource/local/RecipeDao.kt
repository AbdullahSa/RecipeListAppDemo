package com.sample.recipelistapp.data.datasource.local

import androidx.room.*
import com.sample.recipelistapp.data.model.RecipeItem
import com.sample.recipelistapp.data.model.RecipePhoto
import com.sample.recipelistapp.data.model.RecipeWithPhotos
import com.sample.recipelistapp.util.Constants.GET_ALL_RECIPE_LIST_QUERY
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Transaction
    @Insert
    suspend fun addNewRecipeEntity(recipeItem: RecipeItem): Long

    @Insert
    suspend fun addPhotoEntities(photoList: List<RecipePhoto>)

    @Transaction
    @Query(GET_ALL_RECIPE_LIST_QUERY)
    fun getAllRecipeList(): Flow<List<RecipeWithPhotos>>

}