package com.example.sih2024.viewModels

import android.content.Context
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class FarmerViewModel(private val authViewModel: AuthViewModel, private val customerHomeScreenViewModel: CustomerHomeScreenViewModel, val context: Context) : ViewModel() {

    val firestoreReference = authViewModel.firestoreReference
    var selectedGroupIndex = mutableIntStateOf(0)
}