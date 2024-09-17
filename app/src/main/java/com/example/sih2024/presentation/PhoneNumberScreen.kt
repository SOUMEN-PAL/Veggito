package com.example.sih2024.presentation

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sih2024.R
import com.example.sih2024.screens.pages.Pages
import com.example.sih2024.viewModels.AuthViewModel

@Composable
fun PhoneNumberScreen(modifier: Modifier = Modifier , viewModel: AuthViewModel , navController: NavController , activity: Activity) {
    var phoneNumber by viewModel.phoneNumber

    Column {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.phonesignin),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )



            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Row( // Use a Row for horizontal arrangement
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 35.dp),
                    horizontalArrangement = Arrangement.Start // Align to the start
                ) {
                    Spacer(modifier = Modifier.width(20.dp))
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = "Back" // Provide a description for accessibility
                        )
                    }
                    // Add other composables here if needed
                }

                Text(
                    text = "Enter Your Mobile Number",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 45.dp, start = 20.dp, bottom = 25.dp),
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 24.sp
                )
                Text(
                    text = "Mobile Number",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp),
                    fontStyle = FontStyle.Italic,
                    color = Color.Gray
                )
            }
        }




        TextField(
            value = phoneNumber, onValueChange = { phoneNumber = it.filter { it.isDigit() }.take(10)  },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            leadingIcon = {
                Row(
                    modifier = Modifier
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.india1),
                        contentDescription = null,
                        Modifier.size(width = 31.dp, height = 22.dp)
                    )
                    Text(text = "+91")
                }

            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,

                ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

            singleLine = true,

            )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(end = 16.dp , bottom = 16.dp)
                .imePadding(),
            horizontalArrangement = Arrangement.End,
        ) {
            IconButton(
                onClick = {
                    if(phoneNumber.length == 10) {
                        viewModel.getPhoneNumber(phoneNumber)
                        viewModel.sendOtp(activity)
                        navController.navigate(Pages.OTPScreen.route)
                    }else{
                        Toast.makeText(activity , "PLease enter a correct number" , Toast.LENGTH_LONG).show()
                    }
                },
                modifier = Modifier
                    .clip(CircleShape) // Make the button circular
                    .background(colorResource(id = R.color.maingreen))
                    .size(55.dp)) {
                Icon(
                    imageVector = Icons.Filled.ChevronRight,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .clip(CircleShape) // Make the button circular
                        .background(colorResource(id = R.color.maingreen))
                        .size(40.dp)
                )
            }

        }


    }
}


