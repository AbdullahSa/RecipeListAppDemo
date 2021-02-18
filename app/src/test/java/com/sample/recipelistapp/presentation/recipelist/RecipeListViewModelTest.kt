package com.sample.recipelistapp.presentation.recipelist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sample.recipelistapp.data.model.RecipeItem
import com.sample.recipelistapp.data.model.RecipeWithPhotos
import com.sample.recipelistapp.domain.interactor.GetRecipeListUseCase
import com.sample.recipelistapp.presentation.CoroutineTestRule
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class RecipeListViewModelTest {

    private val test = object {
        val numberLong = 0L
        val string = "test"
    }

    @MockK
    lateinit var useCase: GetRecipeListUseCase

    private lateinit var viewModel: RecipeListViewModel

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        MockKAnnotations.init(this)
        viewModel = RecipeListViewModel(useCase)
    }

    @Test
    fun `given listMutableLiveData, when getRecipeList called, then check getting recipe list`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            // Given
            val response = flow {
                emit(
                    listOf(
                        RecipeWithPhotos(
                            RecipeItem(test.numberLong, test.string, test.string),
                            listOf()
                        )
                    )
                )
            }
            coEvery { useCase.getRecipeList() } returns response
            val mockLiveData = spyk<Observer<List<RecipeWithPhotos>>>()
            viewModel.listLiveData.observeForever(mockLiveData)

            // When
            viewModel.getRecipeList()

            // Then
            verify {
                mockLiveData.onChanged(capture(slot()))
            }
            assert(viewModel.listLiveData.value != null)
        }
    }
}