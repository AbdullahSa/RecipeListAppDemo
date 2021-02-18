package com.sample.recipelistapp.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.sample.recipelistapp.data.model.RecipeItem
import com.sample.recipelistapp.data.model.RecipePhoto
import com.sample.recipelistapp.util.Constants.DB_NAME

@Database(entities = [RecipeItem::class, RecipePhoto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(
                        context
                    ).also {
                        INSTANCE = it
                    }
            }

        private fun buildDatabase(context: Context) =
            databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, DB_NAME
            ).build()
    }

}