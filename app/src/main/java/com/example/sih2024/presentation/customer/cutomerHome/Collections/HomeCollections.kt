package com.example.sih2024.presentation.customerHome.Collections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sih2024.presentation.customerHome.Product.Product
import com.example.sih2024.R
import com.example.sih2024.presentation.customerHome.Product.ProductDataItems
import com.example.sih2024.viewModels.CustomerHomeScreenViewModel

@Composable
fun HomeCollections(
    productDataItems: List<ProductDataItems?>,
    customerHomeScreenViewModel: CustomerHomeScreenViewModel,
    category: String
){
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = category, fontSize = 24.sp)
                TextButton(onClick = {  }) {
                    Text(text = "See all", color = colorResource(id = R.color.maingreen))
                }
            }
            LazyRow() {
                items(productDataItems){ product->
                    if (product != null) {
                        Product(product , customerHomeScreenViewModel = customerHomeScreenViewModel)
                    }
                }
            }
        }

    Spacer(modifier = Modifier.height(16.dp))
}

