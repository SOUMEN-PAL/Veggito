package com.example.sih2024.viewModels

import android.content.Context
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sih2024.presentation.customerHome.Product.ProductDataItems
import com.example.sih2024.presentation.customerHome.Product.ProductDataUser
import com.example.sih2024.presentation.farmer.FarmerTransactionModel
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class FarmerViewModel(private val authViewModel: AuthViewModel, private val customerHomeScreenViewModel: CustomerHomeScreenViewModel, val context: Context) : ViewModel() {

    val firestoreReference = authViewModel.firestoreReference
    var selectedGroupIndex = mutableIntStateOf(0)

    val TransactionsList = mutableStateListOf<FarmerTransactionModel?>()
    val ShipmentList = mutableStateListOf<FarmerTransactionModel?>()


    fun fetchTransactions(transactionItems:MutableList<FarmerTransactionModel?>){
        viewModelScope.launch {
            val fetchedProducts = try {
                firestoreReference.collection("Farmers").document(authViewModel.email.value)
                    .collection("Transactions")
                    .get().await()
                    .documents.mapNotNull { it.toObject(FarmerTransactionModel::class.java) }

            }catch (e : FirebaseFirestoreException){
                emptyList()
            }
            TransactionsList.clear()
            TransactionsList.addAll(fetchedProducts)
        }
    }

    fun fetchShipments(shipmentItems:MutableList<FarmerTransactionModel?>){
        viewModelScope.launch {
            val fetchedProducts = try {
                firestoreReference.collection("Farmers").document(authViewModel.email.value)
                    .collection("Shipments")
                    .get().await()
                    .documents.mapNotNull { it.toObject(FarmerTransactionModel::class.java) }

            }catch (e : FirebaseFirestoreException){
                emptyList()
            }
            ShipmentList.clear()
            ShipmentList.addAll(fetchedProducts)
        }
    }
}