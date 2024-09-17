package com.example.sih2024.presentation.customerHome.BottomBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sih2024.screens.pages.Pages
import com.example.sih2024.viewModels.CustomerHomeScreenViewModel

@Composable
fun BottomBar(customerHomeScreenViewModel: CustomerHomeScreenViewModel , navController: NavController){
    var selectedGroupIndex by customerHomeScreenViewModel.selectedGroupIndex
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(72.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            for ((index, group) in groups.withIndex()) {
                Group(
                    group = group,
                    isClicked = selectedGroupIndex == index, // Pass state from parent
                    onGroupClick = {
                        selectedGroupIndex = index
                        if(selectedGroupIndex == 0){
                            navController.navigate(Pages.CustomerHomeScreen.route)
                        }

                        if(selectedGroupIndex == 1){
                            navController.navigate(Pages.CustomerCategoryScreen.route)
                        }
                        if(selectedGroupIndex == 2){
                            navController.navigate(Pages.CartScreen.route)
                        }
                        if(selectedGroupIndex == 4){
                            navController.navigate(Pages.SignOut.route)
                        }

                    } // Update state in parent
                )}

        }
    }
}



