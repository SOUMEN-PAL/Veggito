package com.example.sih2024.presentation.customer.customerFavourite

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sih2024.presentation.customerHome.Product.ProductDataItems
import com.example.sih2024.viewModels.CustomerHomeScreenViewModel


@Composable
fun FavouriteItem(
    productData: ProductDataItems,
    customerHomeScreenViewModel: CustomerHomeScreenViewModel,
    navController: NavController
) {
    Card(
        onClick = {
            //TODO:To Detail Screen
        },
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Red, shape = RectangleShape)
            .height(114.dp),
        shape = RectangleShape
    )
    {
        Box(
            modifier = Modifier.background(color = Color.White)
        ) {

            HorizontalDivider()

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    Image(
                        painter = painterResource(id = customerHomeScreenViewModel.getDrawableResourceId(productData.imageName , context = customerHomeScreenViewModel.context)),
                        contentDescription = null,
                        modifier = Modifier
                            .size(70.dp)
                            .align(Alignment.Bottom)
                    )

                    Column(
                    ) {
                        Column(
                            Modifier.padding(8.dp)
                        ) {
                            Text(
                                text = productData.name,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = productData.description,
                                fontSize = 16.sp,
                                color = Color.Gray
                            )
                        }

                    }
                    Row(

                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = productData.price, fontSize = 16.sp)
                        IconButton(
                            onClick = {
                                customerHomeScreenViewModel.removeFromFavourites(productData.name , onSuccess = {success->

                                })
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                modifier = Modifier.size(26.dp)
                            )
                        }

                    }
                }

            }
        }
    }
}