package com.example.sih2024.presentation.customer.customerExplore

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sih2024.presentation.customer.TextSection
import com.example.sih2024.presentation.customerHome.BottomBar.BottomBar
import com.example.sih2024.presentation.customerHome.SearchSection
import com.example.sih2024.screens.pages.Pages
import com.example.sih2024.viewModels.CustomerHomeScreenViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(navController: NavController , customerHomeScreenViewModel: CustomerHomeScreenViewModel , context: Context){


    LaunchedEffect(Unit) {
        customerHomeScreenViewModel.fetchCategory()
    }

    Scaffold(
        bottomBar = { BottomBar(customerHomeScreenViewModel , navController) },
        topBar =  {
            TopAppBar(title = {
                Column{
                    TextSection("Find Products")
                    SearchSection()
                }
            })

        }
    ) {pv->
        Column(
            Modifier
                .fillMaxSize()
                .padding(pv),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
//            TextSection()
//            SearchSection()

            LazyVerticalGrid(columns = GridCells.Fixed(2),
                modifier = Modifier.padding(start = 16.dp , end = 16.dp , top = 8.dp)
            ) {

                for(categoryName in customerHomeScreenViewModel.categoryList){
                    item {
                        if (categoryName != null) {
                            CategoryCard(
                                category = customerHomeScreenViewModel.categoryDataMap[categoryName],
                                categoryName = categoryName
                            ) {
                                navController.navigate(Pages.CategoryItemsScreen.withArgs(categoryName))
                            }
                        }
                    }
                }

            }

        }
    }

}

