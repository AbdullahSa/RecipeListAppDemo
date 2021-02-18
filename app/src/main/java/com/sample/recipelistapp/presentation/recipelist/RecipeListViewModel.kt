package com.sample.recipelistapp.presentation.recipelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.recipelistapp.data.model.RecipeItem
import com.sample.recipelistapp.data.model.RecipeWithPhotos
import com.sample.recipelistapp.domain.interactor.GetRecipeListUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class RecipeListViewModel @Inject constructor(private val getRecipeListUseCase: GetRecipeListUseCase) :
    ViewModel() {

    private val listMutableLiveData = MutableLiveData<List<RecipeWithPhotos>>()
    val listLiveData: LiveData<List<RecipeWithPhotos>> get() = listMutableLiveData

    private var job: Job? = null

    init {
        getRecipeList()
    }

    private fun getRecipeList() {
        job = CoroutineScope(Dispatchers.IO).launch {
            getRecipeListUseCase.getRecipeList().collect {
                withContext(Dispatchers.Main) {
                    listMutableLiveData.value = it
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}