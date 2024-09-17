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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
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
import com.example.sih2024.viewModels.LocationViewModel


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel,
    locationViewModel: LocationViewModel,
    context: Context
) {

    var email by authViewModel.email

    var password by authViewModel.password

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
                    modifier = Modifier.size(50.dp)
                )
            }

        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Log In",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                fontSize = 24.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "Enter your emails and password",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                fontStyle = FontStyle.Italic,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.weight(0.1f))

            OutlinedTextField(value = email,
                onValueChange = {
                    email = it
                },
                label = {
                    Text(
                        text = "Email", fontStyle = FontStyle.Italic, color = Color.Gray
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

            Spacer(modifier = Modifier.height(8.dp))

            var passwordVisible by remember { mutableStateOf(true) }

            OutlinedTextField(value = password,
                onValueChange = { password = it },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF53B175), unfocusedBorderColor = Color(0xFF53B175)
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                label = {
                    Text(
                        text = "Password", fontStyle = FontStyle.Italic, color = Color.Gray
                    )
                },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff
                    // Please provide the import for the Icons here
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, "")
                    }
                })

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                var ForgotPasswordClicked by remember {
                    mutableStateOf(false)
                }
                Text(
                    text = "Forgot Password?",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.clickable(onClick = {
                        if (email.isNotEmpty()) {
                            authViewModel.sendPasswordResetEmail(email)
                            Toast.makeText(
                                context, "Reset Link Sent on email", Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(context, "Wrong Email", Toast.LENGTH_LONG).show()
                        }
                    }),
                    color = if (ForgotPasswordClicked) colorResource(id = R.color.maingreen) else Color.Black
                )

            }
            Spacer(modifier = Modifier.weight(1f))


            Button(
                onClick = {

                    if (email.isNotEmpty() && authViewModel.isValidEmail()) {
                        authViewModel.login { success ->
                            if (success) {
                                authViewModel.getEmail(email)
                                authViewModel.getPassword(password)
                                authViewModel.LoginUtil(locationViewModel = locationViewModel,
                                    context = context,
                                    onSuccess = { success ->
                                        if (success) {

                                            if (authViewModel.userType.value == "Farmer") {
                                                navController.navigate(Pages.FarmerHomeScreen.route)
                                                navController.popBackStack()
                                            } else if (authViewModel.userType.value == "Customer") {
                                                navController.navigate(Pages.CustomerHomeScreen.route)
                                                navController.popBackStack()
                                            } else if (authViewModel.userType.value == "Agent") {
                                                navController.navigate(Pages.AgentHomeScreen.route)
                                                navController.popBackStack()
                                            }
                                        } else {
                                            Toast.makeText(
                                                context, "Wrong email or password", Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    })
                            } else {
                                Toast.makeText(context, "Invalid Details", Toast.LENGTH_LONG).show()

                            }
                        }
                    }
                    else{
                        Toast.makeText(context , "Invalid Details" ,  Toast.LENGTH_LONG).show()
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
                Text(text = "Log In", fontStyle = FontStyle.Italic)
            }


            var signupOffset by remember { mutableStateOf<Int?>(null) }

            val annotatedText = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Gray,
                        fontWeight = FontWeight.SemiBold,
                        fontStyle = FontStyle.Italic
                    )
                ) {
                    append("Don't have an account? ")
                }
                val startIndex = length
                pushStringAnnotation(tag = "signup", annotation = "signup")
                withStyle(
                    style = SpanStyle(
                        color = colorResource(id = R.color.maingreen),
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )
                ) {
                    append("Signup")
                }
                signupOffset = startIndex
                pop()
            }

            Text(text = annotatedText, modifier = Modifier
                .padding(8.dp)
                .clickable {
                    if (signupOffset != null) {
                        navController.navigate(Pages.signUpMethodScreen.route)
                        Log.d("SignUp", "Sign up page")
                    }
                })


            Spacer(modifier = Modifier.weight(0.3f))
        }
    }
}


