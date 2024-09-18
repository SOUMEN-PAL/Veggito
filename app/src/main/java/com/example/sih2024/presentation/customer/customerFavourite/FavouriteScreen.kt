package com.example.sih2024.presentation.customer.customerFavourite

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sih2024.R
import com.example.sih2024.presentation.customer.TextSection
import com.example.sih2024.presentation.customerHome.BottomBar.BottomBar
import com.example.sih2024.viewModels.AuthViewModel
import com.example.sih2024.viewModels.CustomerHomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen(
    modifier: Modifier,
    navController: NavController,
    customerHomeScreenViewModel: CustomerHomeScreenViewModel,
    authViewModel: AuthViewModel
){

    var favouriteList = customerHomeScreenViewModel.favouriteItems
    LaunchedEffect(Unit) {
        customerHomeScreenViewModel.fetchFavouritesDetails(favouriteList)
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
                        //TODO: navigate to checkout screen
                        if(favouriteList.isNotEmpty()){
                            for (favourite in favouriteList) {
                                if (favourite != null) {
                                    customerHomeScreenViewModel.addData(favourite , onSuccess = {success->
                                        Toast.makeText(customerHomeScreenViewModel.context , "Added to cart" , Toast.LENGTH_LONG).show()
                                    })
                                }
                            }
                            customerHomeScreenViewModel.fetchCartDetails(customerHomeScreenViewModel.cartItems)
                        }
                    },
                    modifier = Modifier.size(width = 353.dp, height = 60.dp),
                    shape = RoundedCornerShape(19.dp),
                    colors = ButtonColors(
                        containerColor = colorResource(id = R.color.maingreen),
                        contentColor = Color.White,
                        disabledContainerColor = colorResource(id = R.color.maingreen),
                        disabledContentColor = Color.White
                    )
                ) {
                    Text(text = "Go to CheckOut")

                }

                BottomBar(customerHomeScreenViewModel = customerHomeScreenViewModel , navController = navController)
            }
        },
        topBar = { TopAppBar(title = { TextSection(text = "Favourites") }) },
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
                items(favouriteList){favourite->
                    if (favourite != null) {
                        FavouriteItem(
                            productData = favourite,
                            customerHomeScreenViewModel = customerHomeScreenViewModel,
                            navController = navController
                        )
                    }
                }
            }

        }
    }
}