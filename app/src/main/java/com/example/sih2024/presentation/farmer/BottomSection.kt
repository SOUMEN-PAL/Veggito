package com.example.sih2024.presentation.farmer

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sih2024.R
import com.example.sih2024.screens.pages.Pages
import com.example.sih2024.viewModels.FarmerViewModel

@Composable
fun BottomSection(
    farmerViewModel: FarmerViewModel,
    context: Context,
    navController: NavController
) {

    val selectedGroupIndex = farmerViewModel.selectedGroupIndex

    Column(
        modifier = Modifier.fillMaxWidth().imePadding().background(color = Color.Transparent)
    ) {
        Box(
            modifier = Modifier.background(color = Color.Transparent)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().background(color = Color.Transparent),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .background(color = Color.Transparent)
                        .clip(RoundedCornerShape(topEnd = 100.dp)),
                    color = colorResource(id = R.color.farnerBottomGreen2)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(end = 50.dp)
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = {

                            navController.navigate(Pages.FarmerHomeScreen.route)


                        }) {
                            Icon(imageVector = Icons.Default.Home,
                                contentDescription = null,
                                modifier = Modifier.size(40.dp),
                                tint = colorResource(id = R.color.white)
                            )
                        }
                        IconButton(onClick = {
                            //TransactionScreen
                            navController.navigate(Pages.TransactionScreen.route)

                        }) {
                            Icon(
                                imageVector = Icons.Default.CalendarToday,
                                contentDescription = null,
                                modifier = Modifier.size(30.dp),
                                tint = colorResource(id = R.color.white)
                            )
                        }
                    }
                }

                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .background(color = Color.Transparent)
                        .clip(RoundedCornerShape(topStart = 100.dp)),
                    color = colorResource(id = R.color.farnerBottomGreen2)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 50.dp)
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = {
                            navController.navigate(Pages.ShipmentScreen.route)

                        }) {
                            Icon(imageVector = Icons.Default.Wallet,
                                contentDescription = null,
                                modifier = Modifier.size(40.dp),
                                tint = colorResource(id = R.color.white)
                            )
                        }
                        IconButton(onClick = {
                            navController.navigate(Pages.SignOut.route)

                        }) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = null,
                                modifier = Modifier.size(30.dp),
                                tint = colorResource(id = R.color.white)
                            )
                        }
                    }

                }
            }

            Button(
                onClick = {
                    navController.navigate(Pages.ListProductFarmers.route)
                },
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = CircleShape)
                    .padding(bottom = 10.dp)
                    .border(width = 4.dp, color = Color.White, CircleShape)
                    .align(Alignment.Center),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = colorResource(id = R.color.farnerplus)
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }

            // Add Spacer with appropriate height below the Button
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}