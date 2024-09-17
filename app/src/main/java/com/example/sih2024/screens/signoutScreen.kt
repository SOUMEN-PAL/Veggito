package com.example.sih2024.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.sih2024.screens.pages.Pages
import com.example.sih2024.viewModels.AuthViewModel
import com.example.sih2024.viewModels.CustomerHomeScreenViewModel

@Composable
fun SignoutScreen(modifier: Modifier = Modifier , authViewModel: AuthViewModel ,customerHomeScreenViewModel: CustomerHomeScreenViewModel , navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            authViewModel.signOut()
            navController.navigate(Pages.onBoardScreen.route)
            customerHomeScreenViewModel.selectedGroupIndex.intValue= 0
        }) {
            Text(text = "SignOut")
        }
    }

}