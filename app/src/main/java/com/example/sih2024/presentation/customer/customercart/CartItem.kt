package com.example.sih2024.presentation.customer.customercart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sih2024.R
import com.example.sih2024.presentation.customerHome.Product.ProductDataUser
import com.example.sih2024.viewModels.AuthViewModel
import com.example.sih2024.viewModels.CustomerHomeScreenViewModel

@Composable
fun CartItem(
    productData: ProductDataUser,
    authViewModel: AuthViewModel,
    customerHomeScreenViewModel: CustomerHomeScreenViewModel,
    onClick :() -> Unit
) {

    var quantity by remember {
        mutableStateOf(productData.userQuantity)
    }

    var itemPrice by remember { mutableStateOf(productData.price.toDouble() * quantity) }

    Card(onClick = { onClick() },
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Red, shape = RectangleShape)
            .height(157.dp),
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
                        painter = painterResource(id =  customerHomeScreenViewModel.getDrawableResourceId(productData.imageName , customerHomeScreenViewModel.context)),
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
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(20.dp)
                        ) {

                            Button(
                                onClick = {
                                    if(quantity>0){
                                        quantity--
                                    }

                                    itemPrice = productData.price.toDouble() * quantity
                                    customerHomeScreenViewModel.updateItemQuantity(productData.name , quantity){success->

                                    }
                                },
                                modifier = Modifier.size(45.dp),
                                shape = RoundedCornerShape(17.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(id = R.color.white)
                                ),
                                contentPadding = PaddingValues(2.dp),
                                border = BorderStroke(
                                    width = 1.dp,
                                    color = Color.LightGray
                                )
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.minus),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                            }


                            Text(text = quantity.toString())


                            Button(
                                onClick = {
                                    quantity++
                                    itemPrice = productData.price.toDouble() * quantity
                                    customerHomeScreenViewModel.updateItemQuantity(productData.name , quantity){success->

                                    }
                                },
                                modifier = Modifier.size(45.dp),
                                shape = RoundedCornerShape(17.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(id = R.color.white),
                                    contentColor = colorResource(id = R.color.maingreen)
                                ),
                                contentPadding = PaddingValues(2.dp),
                                border = BorderStroke(
                                    width = 1.dp,
                                    color = Color.LightGray
                                )
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.greenplus),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                            }


                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            onClick = {
                                customerHomeScreenViewModel.removeFromCart(productData.name , onSuccess = {success->

                                })
                            },
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.cross),
                                contentDescription = null,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                        Text(text = "₹ ${itemPrice}", fontSize = 18.sp)
                    }
                }

            }
        }
    }

}