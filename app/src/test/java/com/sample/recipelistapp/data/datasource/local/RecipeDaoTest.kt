package com.sample.recipelistapp.data.datasource.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.sample.recipelistapp.data.model.RecipeItem
import com.sample.recipelistapp.data.model.RecipePhoto
import com.sample.recipelistapp.presentation.CoroutineTestRule
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
@ExperimentalCoroutinesApi
open class RecipeDaoTest {

    private lateinit var database: AppDatabase

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun writeRecipeAndReadInList() {
        coroutineTestRule.dispatcher.runBlockingTest {
            // Given
            val recipeItem = RecipeItem(1, "", "")
            val photo = RecipePhoto(1, 1, "", false)
            database.recipeDao().addNewRecipeEntity(recipeItem)
            database.recipeDao().addPhotoEntities(arrayListOf(photo))

            // When
            val recipes = database.recipeDao().getAllRecipeList().first()

            // Then
            assertNotNull(recipes)
            assertEquals(recipeItem, recipes[0].recipeItem)
            assertEquals(photo, recipes[0].photoLists[0])
        }
    }
}