package com.sample.recipelistapp.di

import android.content.Context
import com.sample.recipelistapp.data.datasource.local.AppDatabase
import com.sample.recipelistapp.data.datasource.local.RecipeDao
import com.sample.recipelistapp.data.repository.RecipeListRepositoryImpl
import com.sample.recipelistapp.domain.RecipeListRepository
import com.sample.recipelistapp.domain.interactor.GetRecipeListUseCase
import com.sample.recipelistapp.domain.interactor.SaveNewRecipeUseCase
import com.sample.recipelistapp.presentation.saverecipe.SaveRecipeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getInstance(appContext)

    @Singleton
    @Provides
    fun provideRecipeDao(db: AppDatabase): RecipeDao = db.recipeDao()

    @Singleton
    @Provides
    fun provideRepository(recipeDao: RecipeDao): RecipeListRepository =
        RecipeListRepositoryImpl(recipeDao)

    @Singleton
    @Provides
    fun provideGetRecipeListUseCase(repository: RecipeListRepository): GetRecipeListUseCase =
        GetRecipeListUseCase(repository)

    @Singleton
    @Provides
    fun provideSaveNewRecipeUseCase(repository: RecipeListRepository): SaveNewRecipeUseCase =
        SaveNewRecipeUseCase(repository)
}