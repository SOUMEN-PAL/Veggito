package com.example.sih2024.viewModels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sih2024.presentation.customerHome.Product.ProductDataItems
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class CustomerHomeScreenViewModel(
    private val authViewModel: AuthViewModel,
    val context: Context
) : ViewModel() {
    val _exclusiveOfferProducts = mutableStateListOf<ProductDataItems?>()
    val exclusiveOfferProducts: List<ProductDataItems?> = _exclusiveOfferProducts

    val _bestSellingProducts = mutableStateListOf<ProductDataItems?>()
    val bestSellingProducts: List<ProductDataItems?> = _bestSellingProducts

    var firestoreReference = authViewModel.firestoreReference
    var selectedGroupIndex = mutableIntStateOf(0)







    fun fetchProducts(category: String, products: MutableList<ProductDataItems?>) {
        viewModelScope.launch {
            val fetchedProducts = try {
                firestoreReference.collection("Offers").document("connecter").collection(category).get().await()
                    .documents.mapNotNull { it.toObject(ProductDataItems::class.java) }
            } catch (e: FirebaseFirestoreException) {
                // Handleexception
                emptyList()
            }
            products.clear()
            products.addAll(fetchedProducts)
        }
    }



    fun getDrawableResourceId(itemName: String, context: Context): Int {
        return context.resources.getIdentifier(
            itemName, "drawable", context.packageName
        )
    }

}