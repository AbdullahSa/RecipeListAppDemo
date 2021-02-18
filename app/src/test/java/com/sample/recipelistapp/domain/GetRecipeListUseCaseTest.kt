package com.sample.recipelistapp.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sample.recipelistapp.domain.interactor.GetRecipeListUseCase
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

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
open class GetRecipeListUseCaseTest {

    @MockK
    lateinit var repository: RecipeListRepository

    private lateinit var getRecipeListUseCase: GetRecipeListUseCase

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getRecipeListUseCase = GetRecipeListUseCase(repository)
    }

    @Test
    fun `given rule, when getRecipeList called, then verify getAllRecipeList from repository`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            // Given
            coEvery { repository.getAllRecipeList() } returns mockk()

            // When
            getRecipeListUseCase.getRecipeList()

            // Then
            coVerify {
                repository.getAllRecipeList()
            }
        }
    }
}