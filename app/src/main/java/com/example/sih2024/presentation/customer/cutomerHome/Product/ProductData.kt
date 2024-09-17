package com.example.sih2024.presentation.customerHome.Product

import androidx.annotation.DrawableRes
import com.example.sih2024.R

interface Product {
    val name: String
    val price: String
    val description: String
    val details : String
    val imageName : String
}

data class ProductDataItems(
    override val name: String = "",
    override val price: String = "",
    override val description: String = "",
    override val details : String = "",
    override val imageName: String = ""
) : Product

data class ProductDataUser(
    override val name: String = "",
    override val price: String = "",
    override val description: String = "",
    override val details : String = "",
    override val imageName: String = "",
    val userQuantity : Int = 1
) : Product



/*
fun getDrawableResourceId(itemName: String, context: Context): Int {
    return context.resources.getIdentifier(
        itemName, "drawable", context.packageName)
}

// Example usage
val itemNameFromFirebase = "banana" // Replace with actual item name from Firebase

val productData = ProductData(
    name = "Organic Bananas",
    price = "$4.99",
    description = "7pcs, priceg",
    image = getDrawableResourceId(itemNameFromFirebase, LocalContext.current)
)
*/

