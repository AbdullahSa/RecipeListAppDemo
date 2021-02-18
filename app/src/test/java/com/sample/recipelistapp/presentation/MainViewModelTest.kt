package com.sample.recipelistapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.sample.recipelistapp.util.navigation.Event
import com.sample.recipelistapp.util.navigation.RecipePagesDestination
import io.mockk.MockKAnnotations
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyInt

@RunWith(JUnit4::class)
class MainViewModelTest {

    private lateinit var mainViewModel: MainActivityViewModel

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mainViewModel = MainActivityViewModel()
    }

    @Test
    fun `given mockNavigationLiveData, when navigateTo called for back, then verify updating of live data`() {
        // Given
        val mockNavigationLiveData = spyk<Observer<Event<NavController.() -> Any>>>()
        mainViewModel.navigationLiveData.observeForever(mockNavigationLiveData)

        // When
        mainViewModel.navigateTo(RecipePagesDestination.Back, bundleOf(), false)

        // Then
        verify { mockNavigationLiveData.onChanged(capture(slot())) }
    }

    @Test
    fun `given mockNavigationLiveData, when navigateTo called for nav, then verify updating of live data`() {
        // Given
        val mockNavigationLiveData = spyk<Observer<Event<NavController.() -> Any>>>()
        mainViewModel.navigationLiveData.observeForever(mockNavigationLiveData)

        // When
        mainViewModel.navigateTo(RecipePagesDestination.SaveRecipePage, bundleOf(), false)

        // Then
        verify { mockNavigationLiveData.onChanged(capture(slot())) }
    }

}