package com.sample.recipelistapp.presentation.saverecipe

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.recipelistapp.data.model.RecipeItem
import com.sample.recipelistapp.data.model.RecipeWithPhotos
import com.sample.recipelistapp.domain.interactor.SaveNewRecipeUseCase
import com.sample.recipelistapp.util.Constants.URI_TYPE_IMAGE
import kotlinx.coroutines.*
import javax.inject.Inject

class SaveRecipeViewModel @Inject constructor(private val saveNewRecipeUseCase: SaveNewRecipeUseCase) :
    ViewModel() {

    private val stateOfSavingMutableLiveData = MutableLiveData<Boolean>()
    val stateOfSavingLiveData: LiveData<Boolean> get() = stateOfSavingMutableLiveData

    private var job: Job? = null

    fun openGallery(): Intent {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = URI_TYPE_IMAGE
        return intent
    }

    fun saveRecipe(recipeItem: RecipeWithPhotos) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val isSuccess = saveNewRecipeUseCase.saveNewRecipe(recipeItem)
            withContext(Dispatchers.Main) {
                stateOfSavingMutableLiveData.postValue(isSuccess)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}