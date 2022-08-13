package com.example.mealzapp.ui

import MealsCategoriesScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mealzapp.model.response.MealResponse
import com.example.mealzapp.ui.meals.details.MealDetailsScreen
import com.example.mealzapp.ui.meals.details.MealDetailsViewModel
import com.example.mealzapp.ui.theme.MealzAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealzAppTheme {
                FoodzApp()

            }
        }
    }
}

@Composable
private fun FoodzApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "destination_meals_list"){
        composable(route = "destination_meals_list") {
            MealsCategoriesScreen(){mealId ->
                navController.navigate("destination_meals_details/$mealId")
            }
        }
        composable(route = "destination_meals_details/{meal_category_id}",
            arguments = listOf(navArgument("meal_category_id"){
                type = NavType.StringType
            })){
            val viewModel : MealDetailsViewModel = viewModel()
            MealDetailsScreen(meal = viewModel.mealState.value)
        }
    }
}
