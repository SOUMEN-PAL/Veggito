package com.example.sih2024.presentation.customer.cutomerHome.Collections

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sih2024.presentation.customer.TextSection
import com.example.sih2024.presentation.customerHome.BottomBar.BottomBar
import com.example.sih2024.presentation.customerHome.Product.Product
import com.example.sih2024.presentation.customerHome.Product.ProductDataItems
import com.example.sih2024.presentation.customerHome.SearchSection
import com.example.sih2024.viewModels.CustomerHomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeeAllScreen(
    modifier: Modifier = Modifier,
    offerType: String,
    navController: NavController,
    customerHomeScreenViewModel: CustomerHomeScreenViewModel
) {

    var listOfOfferProducts = customerHomeScreenViewModel.OfferProducts



    LaunchedEffect(Unit) {

        if (offerType == "Exclusive Offers") {
            customerHomeScreenViewModel.fetchProducts(offerType, listOfOfferProducts)
        } else if (offerType == "Best Selling") {
            customerHomeScreenViewModel.fetchProducts(offerType, listOfOfferProducts)
        }

    }



    Scaffold(
        bottomBar = { BottomBar(customerHomeScreenViewModel, navController) },
        topBar = {
            TopAppBar(title = {
                Column {
                    TextSection("Find Products")
                    SearchSection()
                }
            })

        }
    ) { pv ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(pv),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
            ) {

                items(listOfOfferProducts) { product ->
                    if (product != null) {
                        Product(
                            productData = product,
                            customerHomeScreenViewModel = customerHomeScreenViewModel
                        )
                    }

                }
            }

        }
    }
}