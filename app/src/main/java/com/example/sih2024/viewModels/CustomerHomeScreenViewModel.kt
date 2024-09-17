package com.example.sih2024.viewModels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sih2024.presentation.customerHome.Product.ProductDataItems
import kotlinx.coroutines.tasks.await

class CustomerHomeScreenViewModel(
    private val authViewModel: AuthViewModel,
    val context: Context
) : ViewModel() {


    var firestoreReference = authViewModel.firestoreReference
    var selectedGroupIndex = mutableIntStateOf(0)
    var offerCategory = mutableStateOf("")


    fun FetchAllItemsInCategory(
        category: String,
        onSuccess: (Boolean) -> Unit
    ): MutableList<ProductDataItems?> {
        val productList = mutableListOf<ProductDataItems?>()
        val documentSnapshot =
            firestoreReference.collection("Offers").document("connecter").collection(category).get()
                .addOnSuccessListener { querySnapshot ->
                    for (document in querySnapshot.documents) {
                        val product = document.toObject(ProductDataItems::class.java)
                        productList.add(product)
                    }

                    offerCategory.value = category
                    onSuccess(true)
                }
        if (productList.isEmpty()) {
            onSuccess(false)
        }
        return productList
    }

    fun getDrawableResourceId(itemName: String, context: Context): Int {
        return context.resources.getIdentifier(
            itemName, "drawable", context.packageName
        )
    }

}