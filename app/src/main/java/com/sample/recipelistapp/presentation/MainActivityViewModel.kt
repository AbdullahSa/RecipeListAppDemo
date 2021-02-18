package com.sample.recipelistapp.presentation

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.sample.recipelistapp.util.navigation.Event
import com.sample.recipelistapp.util.navigation.RecipePagesDestination
import com.sample.recipelistapp.util.navigation.defaultNavOptions

class MainActivityViewModel : ViewModel() {

    private val navigationMutableLiveData = MutableLiveData<Event<NavController.() -> Any>>()
    val navigationLiveData: LiveData<Event<NavController.() -> Any>> get() = navigationMutableLiveData

    fun navigateTo(route: RecipePagesDestination, args: Bundle?, clearStack: Boolean) {
        when {
            route is RecipePagesDestination.Back -> withNavController { popBackStack() }
            clearStack -> withNavController { popBackStack(route.destination, false) }
            else -> withNavController { navigate(route.destination, args, defaultNavOptions) }
        }
    }

    private fun withNavController(block: NavController.() -> Any) {
        navigationMutableLiveData.postValue(Event(block))
    }
}