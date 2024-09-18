package com.example.sih2024.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FarmerViewModelFactory(private val authViewModel: AuthViewModel , private val customerHomeScreenViewModel: CustomerHomeScreenViewModel, val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FarmerViewModel(authViewModel , customerHomeScreenViewModel , context) as T
    }

}