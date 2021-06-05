package com.example.receipeapp.model

import kotlinx.parcelize.Parcelize


data class RecipeResponse(val href: String,
                          val results: List<Result>,
                          val title: String,
                          val version: Double)