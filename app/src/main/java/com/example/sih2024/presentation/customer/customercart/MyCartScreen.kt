package com.example.sih2024.presentation.customer.customercart

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sih2024.R
import com.example.sih2024.presentation.customer.TextSection
import com.example.sih2024.presentation.customerHome.BottomBar.BottomBar
import com.example.sih2024.viewModels.AuthViewModel
import com.example.sih2024.viewModels.CustomerHomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCartScreen(
    modifier: Modifier,
    authViewModel: AuthViewModel,
    customerHomeScreenViewModel: CustomerHomeScreenViewModel,
    navController: NavController,
    context: Context
) {

    val CartItems = customerHomeScreenViewModel.cartItems

    LaunchedEffect(Unit) {
        customerHomeScreenViewModel.fetchCartDetails(cartItemsList = CartItems)
    }


    Scaffold(
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                Button(
                    onClick = {

                    },
                    modifier = Modifier
                        .size(width = 353.dp, height = 60.dp)
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(19.dp),
                    colors = ButtonColors(
                        containerColor = colorResource(id = R.color.maingreen),
                        contentColor = Color.White,
                        disabledContainerColor = colorResource(id = R.color.maingreen),
                        disabledContentColor = Color.White
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(text = "Go to CheckOut" ,
                            modifier = Modifier.weight(1f), textAlign = TextAlign.Center)


                        Box(modifier = Modifier
                            .wrapContentSize(Alignment.CenterEnd)
                            .background(
                                color = colorResource(id = R.color.grey),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(2.dp)

                        ){
                            Column(
                            ) {
                                //TODO: items ka price ka sum
                                Text(text = "₹ ${customerHomeScreenViewModel.totalPrice.intValue }")

                            }


                        }
                    }

                }



                BottomBar(customerHomeScreenViewModel , navController = navController)
            }
        },
        topBar = { TopAppBar(title = { TextSection(text = "My Cart") }) },
    ) { pv ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(pv),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {

                    items(CartItems) {cartItem->
                        if (cartItem != null) {
                            CartItem(
                                productData = cartItem,
                                authViewModel = authViewModel,
                                customerHomeScreenViewModel = customerHomeScreenViewModel
                            ) {

                            }
                        }
                    }


            }

        }
    }
}