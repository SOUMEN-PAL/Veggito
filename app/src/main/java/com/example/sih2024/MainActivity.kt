package com.example.sih2024

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sih2024.presentation.LoginScreen
import com.example.sih2024.presentation.SignUpScreen
import com.example.sih2024.screens.Navigation
import com.example.sih2024.ui.theme.SIH2024Theme
import com.example.sih2024.viewModels.AuthViewModel
import com.example.sih2024.viewModels.CustomerHomeScreenViewModel
import com.example.sih2024.viewModels.CustomerHomeScreenViewModelFactory
import com.example.sih2024.viewModels.FarmerViewModel
import com.example.sih2024.viewModels.FarmerViewModelFactory
import com.example.sih2024.viewModels.LocationViewModel
import com.example.sih2024.viewModels.LocationViewModelFactory

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var authViewModel: AuthViewModel = viewModel()
            var locationViewModel: LocationViewModel =
                viewModel(factory = LocationViewModelFactory(this, authViewModel))
            var customerHomeScreenViewModel: CustomerHomeScreenViewModel = viewModel(
                factory = CustomerHomeScreenViewModelFactory(
                    authViewModel = authViewModel,
                    context = this
                )
            )

            var farmerVIewModel : FarmerViewModel = viewModel(factory = FarmerViewModelFactory(authViewModel = authViewModel , customerHomeScreenViewModel = customerHomeScreenViewModel , this))


            SIH2024Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navigation(
                        viewModel = authViewModel,
                        activity = this,
                        locationViewModel = locationViewModel,
                        customerHomeScreenViewModel = customerHomeScreenViewModel,
                        farmerViewModel = farmerVIewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }

        }
    }
}

