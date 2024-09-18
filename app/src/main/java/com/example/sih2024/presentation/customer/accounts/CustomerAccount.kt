package com.example.sih2024.presentation.customer.accounts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sih2024.presentation.customerHome.Banner.HomeBanner
import com.example.sih2024.presentation.customerHome.Banner.banners
import com.example.sih2024.presentation.customerHome.BottomBar.BottomBar
import com.example.sih2024.presentation.customerHome.Collections.HomeCollections
import com.example.sih2024.presentation.customerHome.LocationSection
import com.example.sih2024.presentation.customerHome.SearchSection
import com.example.sih2024.presentation.farmer.TopBarSection
import com.example.sih2024.presentation.farmer.account.Account
import com.example.sih2024.screens.pages.Pages
import com.example.sih2024.viewModels.AuthViewModel
import com.example.sih2024.viewModels.CustomerHomeScreenViewModel
import com.example.sih2024.viewModels.FarmerViewModel

@Composable
fun CustomerAccount(
    authViewModel: AuthViewModel,
    customerHomeScreenViewModel: CustomerHomeScreenViewModel,
    farmerViewModel: FarmerViewModel,
    navController: NavController,
) {





    Scaffold(
        modifier = Modifier.imePadding(),
        bottomBar = { BottomBar(customerHomeScreenViewModel, navController) },
    ) { pv ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(pv),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(pv),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                TopBarSection("Accounts")

                Account(authViewModel = authViewModel, farmerViewModel = farmerViewModel, customerHomeScreenViewModel = customerHomeScreenViewModel)


            }

        }
    }

}
