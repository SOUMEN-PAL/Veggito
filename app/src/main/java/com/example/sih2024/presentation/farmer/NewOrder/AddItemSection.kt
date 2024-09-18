package com.example.sih2024.presentation.farmer.NewOrder

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sih2024.R
import com.example.sih2024.presentation.customer.customerExplore.CategoryData
import com.example.sih2024.presentation.farmer.FarmerTransactionModel
import com.example.sih2024.viewModels.AuthViewModel
import com.example.sih2024.viewModels.CustomerHomeScreenViewModel
import com.example.sih2024.viewModels.FarmerViewModel

@Composable
fun AddItemSection(
    categories: List<String?>,
    farmerViewModel: FarmerViewModel,
    customerHomeScreenViewModel: CustomerHomeScreenViewModel,
    authViewModel: AuthViewModel,
    navController: NavController
) {
    var expandedCategory by remember { mutableStateOf(false) }
    var selectedTextCategory by remember { mutableStateOf("") }

    var expandedItems by remember { mutableStateOf(false) }
    var selectedTextItems by remember { mutableStateOf("") }

    var productQuantity by remember {
        mutableStateOf("")
    }

    val items = farmerViewModel.itemsList

    LaunchedEffect(Unit) {
        if (selectedTextCategory.length != 0) {
            farmerViewModel.fetchCategoryProducts(selectedTextCategory, items)
        }
    }


    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = selectedTextCategory,
            onValueChange = { selectedTextCategory = it },
            label = { Text("Select Category") },
            trailingIcon = {
                IconButton(onClick = { expandedCategory = !expandedCategory }) {
                    Icon(Icons.Filled.ArrowDropDown, "contentDescription")
                }
            },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp) // Set the desired height here
        )


        OutlinedTextField(
            value = selectedTextItems,
            onValueChange = { selectedTextItems = it },
            label = {
                Text(text = "Select Item")
            },
            trailingIcon = {
                IconButton(onClick = { expandedItems = !expandedItems }) {
                    Icon(Icons.Filled.ArrowDropDown, "contentDescription")
                }
            },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )

        OutlinedTextField(
            value = productQuantity,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    productQuantity = it
                }
            },
            label = { Text(text = "Quantity") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        )


        Spacer(modifier = Modifier.weight(1f))
        
        Button(onClick = {


        }) {
            Text(text = "Upload")
        }

        Spacer(modifier = Modifier.weight(2f))




        if (expandedCategory) {

            DropdownMenu(
                expanded = expandedCategory,
                onDismissRequest = { expandedCategory = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .imePadding()
                    .background(Color.White) // Ensure dropdown is visible
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        text = {
                            if (category != null) {
                                Text(category)
                            }
                        },
                        onClick = {
                            if (category != null) {
                                selectedTextCategory = category
                                selectedTextItems = ""
                            }
                            expandedCategory = false
                            farmerViewModel.fetchCategoryProducts(selectedTextCategory, items)

                        }
                    )
                }
            }
        }


        if (expandedItems) {
            DropdownMenu(
                expanded = expandedItems,
                onDismissRequest = { expandedItems = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.White) // Ensure dropdown is visible
            ) {
                farmerViewModel.itemsList.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            if (item != null) {
                                Text(item.name)
                            }
                        },
                        onClick = {
                            if (item != null) {
                                selectedTextItems = item.name
                            }
                            expandedItems = false
                        }
                    )
                }
            }
        }
    }


}