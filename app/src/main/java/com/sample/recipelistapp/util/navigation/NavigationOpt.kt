package com.sample.recipelistapp.util.navigation

import androidx.navigation.NavOptions
import com.sample.recipelistapp.R

/**
 * Showing Animation of Screens
 */
val defaultNavOptions: NavOptions
    get() {
        return NavOptions.Builder()
            .setEnterAnim(R.anim.nav_default_enter_anim)
            .setExitAnim(R.anim.nav_default_exit_anim)
            .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
            .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
            .build()
    }