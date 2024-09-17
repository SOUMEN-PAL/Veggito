package com.example.sih2024.viewModels

import android.content.Context
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sih2024.R
import com.example.sih2024.presentation.customer.customerExplore.CategoryData
import com.example.sih2024.presentation.customerHome.Product.ProductDataItems
import com.example.sih2024.presentation.customerHome.Product.ProductDataUser
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

    var partialexclusiveOfferProducts = mutableStateListOf<ProductDataItems?>()
    var partialbestSellingProducts = mutableStateListOf<ProductDataItems?>()

    var OfferProducts = mutableStateListOf<ProductDataItems?>()

    val products = mutableStateListOf<ProductDataItems?>()



    var CategoryImageMap = mapOf(
        "Dairy" to R.drawable.dairy,
        "Eggs" to R.drawable.eggs,
        "Fruits" to R.drawable.fruits,
        "Meat" to R.drawable.meatfish,
        "Vegetables" to R.drawable.vegetables
    )

    var CategoryColorMap = mapOf(
        "Dairy" to Color(0x40FDE598),
        "Eggs" to Color(0x40FDE598),
        "Fruits" to Color(0x1A53B175),
        "Meat" to Color(0x40F7A593),
        "Vegetables" to Color(0x1A53B175)
    )

    var CategoryBorderMap = mapOf(
        "Dairy" to Color(0xFFFDE598),
        "Eggs" to Color(0xFFFDE598),
        "Fruits" to Color(0xFF53B175),
        "Meat" to Color(0xFFF7A593),
        "Vegetables" to Color(0xFF53B175)

    )

    var categoryDataMap = CategoryImageMap.map { (category, image) ->
        category to CategoryData(
            image = image,
            color = CategoryColorMap[category] ?: Color.Transparent,
            border = CategoryBorderMap[category] ?: Color.Transparent
        )
    }.toMap()


    var categoryList = mutableStateListOf<String?>()

    fun fetchCategory() {
        viewModelScope.launch {
            try {
                val querySnapshot = firestoreReference.collection("Category").get().await()
                val categoryNames = querySnapshot.documents.mapNotNull { it.id }
                categoryList.clear()
                categoryList.addAll(categoryNames)
            } catch (e: FirebaseFirestoreException) {

            }
        }
    }


    fun fetchPartialProducts(category: String, products: MutableList<ProductDataItems?>) {
        viewModelScope.launch {
            try {
                val collectionRef = firestoreReference.collection("Offers").document("connecter")
                    .collection(category)
                val totalCount = collectionRef.get().await().size()
                var limit = totalCount / 2
                if (limit == 0) {
                    limit = 1
                }
                val fetchedProducts = collectionRef.limit(limit.toLong()).get().await()
                    .documents.mapNotNull { it.toObject(ProductDataItems::class.java) }

                products.clear()
                products.addAll(fetchedProducts)
            } catch (e: FirebaseFirestoreException) {
                // Handle exception

            }
        }
    }


    fun fetchProducts(category: String, products: MutableList<ProductDataItems?>) {
        viewModelScope.launch {
            val fetchedProducts = try {
                firestoreReference.collection("Offers").document("connecter").collection(category)
                    .get().await()
                    .documents.mapNotNull { it.toObject(ProductDataItems::class.java) }
            } catch (e: FirebaseFirestoreException) {
                // Handleexception
                emptyList()
            }
            products.clear()
            products.addAll(fetchedProducts)
        }
    }

    fun fetchCategoryProducts(category: String, categoryproducts: MutableList<ProductDataItems?>) {
        viewModelScope.launch {
            val fetchedProducts = try {
                firestoreReference.collection("Items").document("Connecter").collection(category)
                    .get().await()
                    .documents.mapNotNull { it.toObject(ProductDataItems::class.java) }
            } catch (e: FirebaseFirestoreException) {
                // Handleexception
                emptyList()
            }
            categoryproducts.clear()
            categoryproducts.addAll(fetchedProducts)
        }
    }


    fun getDrawableResourceId(itemName: String, context: Context): Int {
        return context.resources.getIdentifier(
            itemName, "drawable", context.packageName
        )
    }


    //Cart Logic
    fun addData(Product: ProductDataItems , onSuccess : (Boolean) -> Unit) {
        var userCartProduct = ProductDataUser(
            name = Product.name,
            price = Product.price,
            description = Product.description,
            details = Product.details,
            imageName = Product.imageName,
            userQuantity = 1
        )

        firestoreReference.collection("Customers").document(authViewModel.email.value)
            .collection("Cart").document(Product.name).set(userCartProduct).addOnCompleteListener{task->
                if(task.isSuccessful){
                    onSuccess(true)
                }
                else{
                    onSuccess(false)
                }

            }

    }
    var totalPrice = mutableIntStateOf(0)

    fun calculateTotalPrice() {
        totalPrice.intValue = 0
        if (cartItems.isNotEmpty()) {
            for (item in cartItems) {
                val itemQuantity = item?.userQuantity ?: 0
                val itemPrice = item?.price?.toDouble() ?: 0.0
                totalPrice.value += (itemPrice * itemQuantity).toInt()
            }
        }
    }

    var cartItems = mutableStateListOf<ProductDataUser?>()


    fun fetchCartDetails(cartItemsList:MutableList<ProductDataUser?>){
        viewModelScope.launch {
            val fetchedProducts = try {
                firestoreReference.collection("Customers").document(authViewModel.email.value)
                    .collection("Cart")
                    .get().await()
                    .documents.mapNotNull { it.toObject(ProductDataUser::class.java) }

            }catch (e : FirebaseFirestoreException){
                emptyList()
            }
            cartItems.clear()
            cartItems.addAll(fetchedProducts)
            calculateTotalPrice()
        }
    }


    fun removeFromCart(itemName : String , onSuccess : (Boolean) -> Unit){
        viewModelScope.launch {
            try{
                firestoreReference.collection("Customers").document(authViewModel.email.value).collection("Cart").document(itemName).delete().await()

                onSuccess(true)
                fetchCartDetails(cartItems)
            }catch (e : FirebaseFirestoreException){
                onSuccess(false)
            }
            calculateTotalPrice()
        }
    }

    fun updateItemQuantity(itemName: String, newQuantity: Int, onSuccess: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                firestoreReference.collection("Customers").document(authViewModel.email.value)
                    .collection("Cart").document(itemName)
                    .update("userQuantity", newQuantity).await()
                onSuccess(true)
                fetchCartDetails(cartItems) // Refresh cart items after successful update
            } catch (e: FirebaseFirestoreException) {
                onSuccess(false)
                // Handle failure: You might want to show a Snackbar or Toast message here
            }
        }
    }


}