package com.sample.recipelistapp.util.navigation

import androidx.annotation.IdRes
import com.sample.recipelistapp.R

// For Fragments to navigate
sealed class RecipePagesDestination(@IdRes val destination: Int) {
    object SaveRecipePage :
        RecipePagesDestination(R.id.action_recipeListFragment_to_saveRecipeFragment)

    object Back : RecipePagesDestination(-1)
}