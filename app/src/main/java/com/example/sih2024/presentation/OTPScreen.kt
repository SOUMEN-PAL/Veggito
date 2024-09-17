package com.example.sih2024.presentation

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sih2024.R
import com.example.sih2024.screens.pages.Pages
import com.example.sih2024.viewModels.AuthViewModel

@Composable
fun OTPScreen(modifier: Modifier = Modifier , navController: NavController , viewModel: AuthViewModel , activity : Activity){

    var otp by viewModel.otp


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
                    text = "Enter Your 6-digit code",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 45.dp, start = 20.dp, bottom = 25.dp),
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 24.sp
                )
                Text(
                    text = "Code",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp),
                    fontStyle = FontStyle.Italic,
                    color = Color.Gray
                )
            }
        }


        BasicTextField(
            value = otp,
            onValueChange = {
                if(it.length <= 6){
                    otp = it
                }
            },
            keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.padding(start = 16.dp)
        ){
            Row (
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ){
                repeat(6){index->
                    val number = when{
                        index >= otp.length -> ""
                        else -> otp[index]
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = number.toString(),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Box(modifier = Modifier
                            .width(40.dp)
                            .height(2.dp)
                            .background(Color.Black)
                        )
                    }
                }
            }
        }




//        BasicTextField(
//            value = phoneNumber,
//            onValueChange = {
//                phoneNumber = it.filter { it.isDigit() }.take(6)
//            },modifier = Modifier
//                .focusRequester(focusRequester)
//                .fillMaxWidth()
//                .padding(16.dp),
//            singleLine = true,
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//            decorationBox = { innerTextField ->
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceAround
//                ) {
//                    for (i in 0..5) {
//                        Box(
//                            modifier = Modifier
//                                .clickable { focusRequester.requestFocus() } // Add this line
//                                .width(40.dp)
//                                .padding(4.dp)
//                                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp)),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Text(
//                                text = if (i < phoneNumber.length) phoneNumber[i].toString() else "_",
//                                fontSize = 20.sp
//                            )
//                        }
//                    }
//                }
//                if (phoneNumber.isEmpty()) {
//                    innerTextField()
//                }
//            }
//        )
//
//

//
//        TextField(
//            value = phoneNumber,
//            onValueChange = {
//                phoneNumber = it.filter { it.isDigit() }.take(6)
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            colors = TextFieldDefaults.colors(
//                unfocusedContainerColor = Color.White,
//                focusedContainerColor = Color.White,
//                focusedTextColor = Color.Black,
//                unfocusedTextColor = Color.Black,
//
//                ),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//
//            singleLine = true
//        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(end = 16.dp, bottom = 16.dp)
                .imePadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Resend Code",
                modifier = Modifier.padding(start = 16.dp).clickable {
                    viewModel.sendOtp(activity)
                },
                color = colorResource(id = R.color.maingreen),
                fontStyle = FontStyle.Italic

            )


            IconButton(
                onClick = {
                    if(otp.length == 6){
                        viewModel.getOtp(otp)
                        viewModel.verifyOTP { sucess->
                            if(sucess){
                                navController.navigate(Pages.LocationScreen.route)
                                Toast.makeText(activity , "Verification Done" , Toast.LENGTH_LONG).show()
                            }
                            else{
                                Toast.makeText(activity , "OTP Invalid" , Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                    else{
                        Toast.makeText(activity , "Enter OTP first" , Toast.LENGTH_LONG).show()
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



