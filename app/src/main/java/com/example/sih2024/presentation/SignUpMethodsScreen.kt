package com.example.sih2024.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sih2024.R
import com.example.sih2024.screens.pages.Pages


@Composable
fun SignUpMethodsScreen(navController: NavController) {


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.4f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.signin),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 50.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Get your groceries\n" + "with nectar",
                fontSize = 26.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )



            Column(
                modifier = Modifier
                    .padding(vertical = 32.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text(text = "Select your SignUp Method")
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = {
                        navController.navigate(Pages.PhoneNumberScreen.route)
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
//                        Image(painter = painterResource(id = R.drawable.google), contentDescription = null,
//                            Modifier
//                                .size(20.dp)
//                                .weight(1f))
                        Icon(
                            imageVector = Icons.Filled.Phone,
                            contentDescription = null,
                            modifier = Modifier.weight(1f)
                        )



                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "Continue with Email and Phone", Modifier.weight(2.5f))
                    }
                }






                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.size(width = 353.dp, height = 60.dp),
                    shape = RoundedCornerShape(19.dp),
                    colors = ButtonColors(
                        containerColor = colorResource(id = R.color.googleButton),
                        contentColor = Color.White,
                        disabledContainerColor = colorResource(id = R.color.googleButton),
                        disabledContentColor = Color.White
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.google),
                            contentDescription = null,
                            Modifier
                                .size(20.dp)
                                .weight(1f)
                        )
//                        Spacer(modifier = Modifier.width(40.dp))
                        Text(text = "Continue with Google", Modifier.weight(2.5f))
                    }

                }
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = Color.LightGray
                    )
                    Text(
                        text = "OR",
                        modifier = Modifier.padding(horizontal = 8.dp),
                        color = Color.Gray
                    )
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        color = Color.LightGray
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
                var signupOffset by remember { mutableStateOf<Int?>(null) }
                val annotatedText2 = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Gray,
                            fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic
                        )
                    ) {
                        append("Already have an account? ")
                    }
                    val startIndex = length
                    pushStringAnnotation(tag = "login", annotation = "login")
                    withStyle(
                        style = SpanStyle(
                            color = colorResource(id = R.color.maingreen),
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        )
                    ) {
                        append("login")
                    }
                    signupOffset = startIndex
                    pop()
                }

                Text(
                    text = annotatedText2,
                    modifier = Modifier

                        .clickable {
                            if (signupOffset != null) {
                                navController.navigate(Pages.LoginScreen.route)
                            }

                        }
                )
                Spacer(modifier = Modifier.weight(1f))


//                Button(onClick = { /*TODO*/ },
//                    modifier = Modifier.size(width = 353.dp, height = 60.dp),
//                    shape = RoundedCornerShape(19.dp),
//                    colors = ButtonColors(
//                        containerColor = colorResource(id = R.color.facebookButton),
//                        contentColor = Color.White,
//                        disabledContainerColor = colorResource(id = R.color.facebookButton),
//                        disabledContentColor = Color.White
//                    )
//                ) {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Start
//                    ) {
//                        Image(painter = painterResource(id = R.drawable.facebook), contentDescription = null,
//                            Modifier
//                                .size(20.dp)
//                                .weight(1f))
//                        Text(text = "Continue with facebook", Modifier.weight(2.5f))
//                    }
//
//                }
            }


        }

    }
}


