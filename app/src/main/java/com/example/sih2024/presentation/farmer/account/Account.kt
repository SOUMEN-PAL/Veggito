package com.example.sih2024.presentation.farmer.account

import android.accounts.Account
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sih2024.R
import com.example.sih2024.screens.pages.Pages
import com.example.sih2024.viewModels.AuthViewModel
import com.example.sih2024.viewModels.CustomerHomeScreenViewModel
import com.example.sih2024.viewModels.FarmerViewModel

@Composable
fun Account(modifier: Modifier = Modifier.fillMaxSize() , authViewModel: AuthViewModel , farmerViewModel: FarmerViewModel , customerHomeScreenViewModel: CustomerHomeScreenViewModel , navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        DetailView(modifier = modifier, string1 = "User Name", string2 = authViewModel.name.value)
        DetailView(modifier = modifier, string1 = "Mobile Number", string2 =authViewModel.phoneNumber.value )
        DetailView(modifier = modifier, string1 = "Email", string2 = authViewModel.email.value)

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                authViewModel.signOut()
                navController.navigate(Pages.onBoardScreen.route)
            },
            modifier = Modifier.size(width = 253.dp, height = 60.dp),
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
                horizontalArrangement = Arrangement.Start
            ) {

//                        Spacer(modifier = Modifier.width(40.dp))
                Text(text = "Sign Out", modifier = Modifier.fillMaxWidth() , textAlign = TextAlign.Center)
            }

        }

        Spacer(modifier = Modifier.weight(1f))
    }
}


