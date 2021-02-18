package com.sample.recipelistapp.presentation.saverecipe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sample.recipelistapp.data.model.RecipeItem
import com.sample.recipelistapp.data.model.RecipeWithPhotos
import com.sample.recipelistapp.domain.interactor.SaveNewRecipeUseCase
import com.sample.recipelistapp.presentation.CoroutineTestRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class SaveRecipeViewModelTest {

    private val test = object {
        val numberLong = 0L
        val string = "test"
    }

    @MockK
    lateinit var useCase: SaveNewRecipeUseCase

    private lateinit var viewModel: SaveRecipeViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        MockKAnnotations.init(this)
        viewModel = SaveRecipeViewModel(useCase)
    }

    @Test
    fun `given stateOfSavingMutableLiveData, when saveRecipe called, then check saved recipe`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            // Given
            val response = true
            val model = RecipeWithPhotos(RecipeItem(test.numberLong, test.string, test.string), listOf())
            coEvery { useCase.saveNewRecipe(any()) } returns response
            val mockLiveData = spyk<Observer<Boolean>>()
            viewModel.stateOfSavingLiveData.observeForever(mockLiveData)

            // When
            viewModel.saveRecipe(model)

            // Then
            verify {
                mockLiveData.onChanged(capture(slot()))
            }
            assert(viewModel.stateOfSavingLiveData.value == true)
        }
    }
}