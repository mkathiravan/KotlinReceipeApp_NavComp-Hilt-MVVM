package com.example.receipeapp.api

import com.example.receipeapp.model.RecipeResponse
import com.example.receipeapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getRecipe():Response<RecipeResponse>


}