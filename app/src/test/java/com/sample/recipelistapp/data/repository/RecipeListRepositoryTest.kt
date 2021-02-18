package com.sample.recipelistapp.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.recipelistapp.data.datasource.local.RecipeDao
import com.sample.recipelistapp.data.model.RecipeItem
import com.sample.recipelistapp.data.model.RecipeWithPhotos
import com.sample.recipelistapp.domain.RecipeListRepository
import com.sample.recipelistapp.presentation.CoroutineTestRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RecipeListRepositoryTest {

    @MockK
    lateinit var localDataSource: RecipeDao

    private lateinit var recipeListRepository: RecipeListRepository

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        recipeListRepository = RecipeListRepositoryImpl(localDataSource)
    }

    @Test
    fun `given rule, when getAllRecipeList called, then verify getAllRecipeList from localDataSource`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            // Given
            coEvery { localDataSource.getAllRecipeList() } returns mockk()

            // When
            recipeListRepository.getAllRecipeList()

            // Test
            coVerify {
                localDataSource
                    .getAllRecipeList()
            }
        }
    }
}