package com.example.receipeapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.receipeapp.model.RecipeResponse
import com.example.receipeapp.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(private val repository: RecipeRepository) : ViewModel()
{
    private val _response = MutableLiveData<RecipeResponse>()
    val recipeResponse: LiveData<RecipeResponse> get() = _response

    init {
        Log.d("ViewModel_called","ViewModel_called");
        getRecipe()
    }

    private fun getRecipe() = viewModelScope.launch {
        repository.getRecipe().let { response ->
            if(response.isSuccessful)
            {
                _response.postValue(response.body())
                Log.d(" RESPONE_BODY "," RESPONE_BODY " + response.body());
            }
            else
            {
                Log.d("response_error","getRecipe : ${response.code()}")
            }
        }
    }

}
