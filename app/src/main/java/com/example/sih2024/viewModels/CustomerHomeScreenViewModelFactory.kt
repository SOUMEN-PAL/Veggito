package com.example.sih2024.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CustomerHomeScreenViewModelFactory(private val authViewModel: AuthViewModel , private val context: Context):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CustomerHomeScreenViewModel(authViewModel , context) as T
    }
}