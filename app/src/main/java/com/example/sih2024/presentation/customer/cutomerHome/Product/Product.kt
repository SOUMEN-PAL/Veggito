package com.example.sih2024.presentation.customerHome.Product

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sih2024.R
import com.example.sih2024.viewModels.CustomerHomeScreenViewModel

@Composable
fun Product(
    productData: ProductDataItems,
    modifier: Modifier = Modifier.padding(6.dp),
    customerHomeScreenViewModel: CustomerHomeScreenViewModel
) {


    var isFavorite by remember { mutableStateOf(false) }

    Card(
        onClick = { /*TODO:detail view*/


        },
        modifier = modifier
//            .padding(8.dp)
            .size(width = 173.dp, height = 260.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.temCard)
        ),
        border = BorderStroke(1.dp, Color.Gray),
        shape = RoundedCornerShape(16.dp)
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
        ) {

            IconButton(onClick = {
                isFavorite = !isFavorite
                customerHomeScreenViewModel.addToFavourites(productData, onSuccess = { sucess ->
                    if (sucess) {
                        Toast.makeText(
                            customerHomeScreenViewModel.context,
                            "Added to favourites",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            customerHomeScreenViewModel.context,
                            "Already in favourites",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
                //TODO:Add to Products

            }, modifier = Modifier.align(Alignment.End)) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "favourite",
                    tint = if (isFavorite) Color.Red else Color.Gray
                )
            }

            Image(
                painter = painterResource(
                    id = customerHomeScreenViewModel.getDrawableResourceId(
                        itemName = productData.imageName,
                        context = customerHomeScreenViewModel.context
                    )
                ),
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(16f / 9f)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(20.dp))
            Text(text = productData.name, fontSize = 16.sp)
            Text(
                text = productData.description,
                fontSize = 14.sp,
                color = colorResource(id = R.color.grey)
            )



            Spacer(Modifier.weight(1f))


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(text = productData.price, fontSize = 18.sp)


                Button(
                    onClick = {
                        customerHomeScreenViewModel.addData(productData, onSuccess = { success ->
                            if (success) {
                                Toast.makeText(
                                    customerHomeScreenViewModel.context,
                                    "Added to Cart",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    customerHomeScreenViewModel.context,
                                    "Unable to add Cart",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        })
                    },
                    modifier = Modifier.size(45.dp),
                    shape = RoundedCornerShape(17.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.maingreen),
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(2.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }


            }


        }
    }
    Spacer(Modifier.width(10.dp))
}


