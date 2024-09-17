package com.example.sih2024.presentation.customerHome

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
import com.example.sih2024.presentation.customer.cutomerHome.Collections.SeeAllScreen
import com.example.sih2024.presentation.customerHome.Banner.HomeBanner
import com.example.sih2024.presentation.customerHome.Banner.banners
import com.example.sih2024.presentation.customerHome.BottomBar.BottomBar
import com.example.sih2024.presentation.customerHome.Collections.HomeCollections
import com.example.sih2024.presentation.customerHome.Product.ProductDataItems
import com.example.sih2024.screens.pages.Pages
import com.example.sih2024.viewModels.AuthViewModel
import com.example.sih2024.viewModels.CustomerHomeScreenViewModel

@Composable
fun CustomerHomeScreen(
    authViewModel: AuthViewModel,
    customerHomeScreenViewModel: CustomerHomeScreenViewModel,
    navController: NavController
) {


    val partialexclusiveOfferProducts = customerHomeScreenViewModel.partialexclusiveOfferProducts
    val partialbestSellingProducts = customerHomeScreenViewModel.partialbestSellingProducts


    LaunchedEffect(Unit) {

        customerHomeScreenViewModel.fetchPartialProducts(
            "Exclusive Offers",
            customerHomeScreenViewModel.partialexclusiveOfferProducts
        )
        customerHomeScreenViewModel.fetchPartialProducts(
            "Best Selling",
            customerHomeScreenViewModel.partialbestSellingProducts
        )
    }


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
            val district = authViewModel.district
            val state = authViewModel.state
            LocationSection(district = district.value, state = state.value)
            SearchSection()
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                item { HomeBanner(banners = banners) }



                item {
                    HomeCollections(
                        partialexclusiveOfferProducts,
                        customerHomeScreenViewModel,
                        "Exclusive Offers",
                        onClick = {
                            navController.navigate(Pages.SeeAllScreen.withArgs("Exclusive Offers"))
                        })
                }


                item {
                    HomeCollections(
                        partialbestSellingProducts,
                        customerHomeScreenViewModel,
                        "Best Selling",
                        onClick = {
                            navController.navigate(Pages.SeeAllScreen.withArgs("Best Selling"))
                        })
                }
            }

        }
    }

}

