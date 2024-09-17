package com.example.sih2024.presentation

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sih2024.R
import com.example.sih2024.models.User
import com.example.sih2024.screens.pages.Pages
import com.example.sih2024.viewModels.AuthViewModel
import com.google.firebase.firestore.GeoPoint

@Composable
fun SignUpScreen(modifier: Modifier = Modifier , authViewModel: AuthViewModel , context: Context , navController: NavController) {




    var userName by authViewModel.name

    var email by authViewModel.email

    var password by authViewModel.password

    var userType by authViewModel.userType

    var selectedButton by remember { mutableStateOf<String?>(null) }





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
                    .height(200.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.trolley2),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sign Up",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                fontSize = 24.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "Enter your credential to continue",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                fontStyle = FontStyle.Italic,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.weight(1f))






            OutlinedTextField(
                value = userName,
                onValueChange = {
                    userName = it
                },
                label = {
                    Text(
                        text = "Username",
                        fontStyle = FontStyle.Italic,
                        color = Color.Gray
                    )
                },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF53B175),
                    unfocusedBorderColor = Color(0xFF53B175),

                    ),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                textStyle = TextStyle(
                    fontSize = 16.sp // Use the same font size
                )
            )





            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = {
                    Text(
                        text = "Email",
                        fontStyle = FontStyle.Italic,
                        color = Color.Gray
                    )
                },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF53B175),
                    unfocusedBorderColor = Color(0xFF53B175),

                    ),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )




            var passwordVisible by remember { mutableStateOf(true) }

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF53B175),
                    unfocusedBorderColor = Color(0xFF53B175)
                ), modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                label = {
                    Text(
                        text = "Password",
                        fontStyle = FontStyle.Italic,
                        color = Color.Gray
                    )
                },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff
                    // Please provide the import for the Icons here
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, "")
                    }
                }
            )

            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "What type of user are you?",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                fontStyle = FontStyle.Italic,
                color = Color.Gray
            )

            //Button Rows
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {


                Button(
                    onClick = {
                        selectedButton = "Farmer"
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(19.dp),
                    colors = ButtonColors(
                        containerColor = if (selectedButton == "Farmer") colorResource(id = R.color.googleButton) else colorResource(
                            id = R.color.maingreen
                        ),
                        contentColor = Color.White,
                        disabledContainerColor = colorResource(id = R.color.maingreen),
                        disabledContentColor = Color.White
                    )
                ) {
                    Text(text = "Farmer")
                }


                Button(
                    onClick = {
                        selectedButton = "Customer"
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(19.dp),
                    colors = ButtonColors(
                        containerColor = if (selectedButton == "Customer") colorResource(id = R.color.googleButton) else colorResource(
                            id = R.color.maingreen
                        ),
                        contentColor = Color.White,
                        disabledContainerColor = colorResource(id = R.color.maingreen),
                        disabledContentColor = Color.White
                    )
                ) {
                    Text(text = "Customer")
                }



                Button(
                    onClick = {
                        selectedButton = "Agent"
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(19.dp),
                    colors = ButtonColors(
                        containerColor = if (selectedButton == "Agent") colorResource(id = R.color.googleButton) else colorResource(
                            id = R.color.maingreen
                        ),
                        contentColor = Color.White,
                        disabledContainerColor = colorResource(id = R.color.maingreen),
                        disabledContentColor = Color.White
                    )
                ) {
                    Text(text = "Agent", textAlign = TextAlign.Center)
                }
            }

            //Button row ended here

            Spacer(modifier = Modifier.weight(1f))

            val annotatedText = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append("By continuing you agree to our ")
                }
                withStyle(style = SpanStyle(color = colorResource(id = R.color.maingreen))) {
                    append("Terms of Service")
                }
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append(" and ")
                }
                withStyle(style = SpanStyle(color = colorResource(id = R.color.maingreen))) {
                    append("Privacy Policy")
                }
            }

            Text(
                text = annotatedText,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp , end = 16.dp , bottom = 8.dp),
                fontStyle = FontStyle.Italic
            )



            Button(
                onClick = {

                    if(userName.isEmpty()){
                        Toast.makeText(context , "Please enter your name" , Toast.LENGTH_LONG).show()
                    }
                    if(password.isEmpty()){
                        Toast.makeText(context , "Please set a password" , Toast.LENGTH_LONG).show()
                    }
                    if(selectedButton == null){
                        Toast.makeText(context , "Please select your catagory" , Toast.LENGTH_LONG).show()
                    }

                    if(userName.isNotEmpty() && password.isNotEmpty() && selectedButton != null){
                        authViewModel.setUserType(selectedButton!!)
                        authViewModel.getEmail(email)
                        authViewModel.getPassword(password)
                        val userGeoPoint = GeoPoint(authViewModel.latitude.doubleValue , authViewModel.longitude.doubleValue)
                        val userData = User(
                            name = userName ,
                            email = email ,
                            phoneNumber = authViewModel.phoneNumber.value,
                            userType = userType,
                            location =  userGeoPoint,
                            address = authViewModel.location.value,
                            state = authViewModel.state.value,
                            district = authViewModel.district.value
                            )
                        authViewModel.linkPhoneCredential(userData , onResult = {success->
                            if(success){
                                //TODO NavController to userTypeHomePage
                                Toast.makeText(context , "Successfully Signed UP" , Toast.LENGTH_LONG).show()
                                if(userType == "Farmer"){
                                    navController.navigate(Pages.FarmerHomeScreen.route)

                                }
                                else if(userType == "Agent"){
                                    navController.navigate(Pages.AgentHomeScreen.route)

                                }
                                else if(userType == "Customer"){
                                    navController.navigate(Pages.CustomerHomeScreen.route)

                                }

                            }else{
                                Toast.makeText(context , "User already Exist" , Toast.LENGTH_LONG).show()
                            }
                        })
                    }


                },
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                shape = RoundedCornerShape(19.dp),
                colors = ButtonColors(
                    containerColor = colorResource(id = R.color.maingreen),
                    contentColor = Color.White,
                    disabledContainerColor = colorResource(id = R.color.maingreen),
                    disabledContentColor = Color.White
                )
            ) {
                Text(text = "Sign Up", fontStyle = FontStyle.Italic)
            }



            Spacer(modifier = Modifier.weight(2f))

        }


    }
}


