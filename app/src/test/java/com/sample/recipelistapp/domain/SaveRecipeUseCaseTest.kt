package com.sample.recipelistapp.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.recipelistapp.data.model.RecipeItem
import com.sample.recipelistapp.data.model.RecipeWithPhotos
import com.sample.recipelistapp.domain.interactor.SaveNewRecipeUseCase
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
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.ArgumentMatchers.anyString

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
open class SaveRecipeUseCaseTest {

    @MockK
    lateinit var repository: RecipeListRepository

    private lateinit var saveNewRecipeUseCase: SaveNewRecipeUseCase

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        saveNewRecipeUseCase = SaveNewRecipeUseCase(repository)
    }

    @Test
    fun `given param, when saveNewRecipe called, then verify saveNewRecipe from repository`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            // Given
            val param = RecipeWithPhotos(
                RecipeItem(anyLong(), anyString(), anyString()),
                listOf()
            )
            coEvery { repository.saveNewRecipe(any()) } returns mockk()

            // When
            saveNewRecipeUseCase.saveNewRecipe(param)

            // Then
            coVerify {
                repository.saveNewRecipe(any())
            }
        }
    }
}