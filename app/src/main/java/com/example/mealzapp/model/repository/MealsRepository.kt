package com.example.mealzapp.model.repository

import com.example.mealzapp.model.api.MealsWebService
import com.example.mealzapp.model.response.MealsCategoriesResponse

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    suspend fun getMeals(): MealsCategoriesResponse {
        return webService.getMeals()
    }
}