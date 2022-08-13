package com.example.mealzapp.ui.meals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealzapp.model.repository.MealsRepository
import com.example.mealzapp.model.response.MealResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MealsCategoryViewModel(private val repository: MealsRepository = MealsRepository.getInstance()) : ViewModel() {

    init {

        viewModelScope.launch(Dispatchers.IO) {
            val meals = getMeals()
            state.value = meals
        }
    }

    val state: MutableState<List<MealResponse>> = mutableStateOf(emptyList<MealResponse>())

    private suspend fun getMeals(): List<MealResponse> {
        return repository.getMeals().categories
    }
}