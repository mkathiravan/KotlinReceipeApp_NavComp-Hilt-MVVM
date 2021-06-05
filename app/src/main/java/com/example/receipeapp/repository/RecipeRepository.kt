package com.example.receipeapp.repository

import com.example.receipeapp.api.ApiService
import javax.inject.Inject

class RecipeRepository
@Inject constructor(private val apiService: ApiService)
{
    suspend fun getRecipe() = apiService.getRecipe()
}