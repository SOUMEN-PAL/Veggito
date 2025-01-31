package com.example.sih2024.presentation.farmer.NewOrder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.sih2024.presentation.farmer.BottomSection
import com.example.sih2024.presentation.farmer.TopBarSection
import com.example.sih2024.viewModels.AuthViewModel
import com.example.sih2024.viewModels.CustomerHomeScreenViewModel
import com.example.sih2024.viewModels.FarmerViewModel

@Composable
fun OrderScreen(
    authViewModel: AuthViewModel,
    customerHomeScreenViewModel: CustomerHomeScreenViewModel,
    farmerViewModel: FarmerViewModel,
    navController: NavController
){
    val category = farmerViewModel.categoryData

    LaunchedEffect(Unit) {
        farmerViewModel.fetchCategory()
    }


    Scaffold(
        bottomBar = { BottomSection(farmerViewModel , customerHomeScreenViewModel.context , navController) }
    ){pv->
        Column(
            Modifier
                .fillMaxSize()
                .padding(pv),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            TopBarSection("New Order")
            AddItemSection(categories = category, navController = navController , authViewModel = authViewModel , farmerViewModel = farmerViewModel , customerHomeScreenViewModel = customerHomeScreenViewModel)
        }

    }
}