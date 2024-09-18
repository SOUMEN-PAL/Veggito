package com.example.sih2024.presentation.farmer.Home

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
import com.example.sih2024.presentation.farmer.TransactionSection
import com.example.sih2024.viewModels.AuthViewModel
import com.example.sih2024.viewModels.CustomerHomeScreenViewModel
import com.example.sih2024.viewModels.FarmerViewModel

@Composable
fun FarmerHomeScreen(
    authViewModel: AuthViewModel,
    customerHomeScreenViewModel: CustomerHomeScreenViewModel,
    farmerViewModel: FarmerViewModel,
    navController: NavController
){
    val transactionList = farmerViewModel.TransactionsList
    LaunchedEffect(Unit) {
        farmerViewModel.fetchTransactions(transactionList)
    }

    Scaffold(
        bottomBar = { BottomSection(farmerViewModel = farmerViewModel , navController = navController , context = customerHomeScreenViewModel.context) }
    ){pv->
        Column(
            Modifier
                .fillMaxSize()
                .padding(pv),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            TopBarSection("Dashboard")
            CardSection()
            GraphSection()
            TransactionSection("Recent Transactions" , transactionList)
        }

    }
}