package com.example.sih2024.presentation

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.navigation.NavController
import com.example.sih2024.R
import com.example.sih2024.screens.LocationPermissionTextProvider
import com.example.sih2024.screens.PermissionDialog
import com.example.sih2024.screens.pages.Pages
import com.example.sih2024.viewModels.AuthViewModel
import com.example.sih2024.viewModels.LocationViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun LocationScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel,
    locationViewModel: LocationViewModel,
    activity: Activity,
    navController: NavController
) {

    val context = LocalContext.current
    val packageName = context.packageName
    val address = authViewModel.location
    var isLoading by remember { mutableStateOf(false) }


    //permission handling
    val permissionToRequest = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    val dialogQueue = locationViewModel.visiblePermissionDialogueQueue

    val permissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            permissionToRequest.forEach { permission ->
                locationViewModel.onPermissionResult(
                    permission = permission,
                    isGranted = permissions[permission] == true
                )
            }
        }
    )

    LaunchedEffect(Unit) {
        permissionResultLauncher.launch(
            permissionToRequest
        )
    }


    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.phonesignin),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row( // Use a Row for horizontal arrangement
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 35.dp),
                    horizontalArrangement = Arrangement.Start // Align to the start
                ) {
                    Spacer(modifier = Modifier.width(20.dp))
                    // Add other composables here if needed
                }

                Image(
                    painter = painterResource(id = R.drawable.locationmap),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f),

            ) {

            Image(
                painter = painterResource(id = R.drawable.bottomimage),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Select Your Location",
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = "Switch on your location to sty tune with\nwhat's happening in your area",
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Spacer(modifier = modifier.weight(1f))

                Text(text = "Your Location")


                OutlinedTextField(
                    value = address.value,
                    onValueChange = {
                        address.value = it
                    },
                    label = {
                        Text(text = "Location")
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF53B175),
                        focusedLabelColor = Color(0xFF53B175),
                        unfocusedBorderColor = Color(0xFF5383EC),
                        unfocusedLabelColor = Color(0xFF5383EC)
                    ),
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    trailingIcon = {

                        if (isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = Color.Red
                            )
                        }

                    }
                )

                Spacer(modifier = modifier.weight(2f))

                Button(
                    onClick = { /*Location permission and conver and acess it*/

                        isLoading = true // Show loading indicator
                        if (locationViewModel.hasLocationPermission()) {
                            locationViewModel.requestLocationUpdates(onSuccess = { success ->
                                if (success) {
                                    locationViewModel.reverseGeoCodeLocation()
                                }
                                isLoading = false // Hide loading indicator
                            })
                        } else {
                            Toast.makeText(activity, "Turn On GPS", Toast.LENGTH_LONG).show()
                            isLoading = false // Hide loading indicator
                        }

                    },
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
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = null,

                            Modifier
                                .size(25.dp)
                                .weight(1f)
                        )
//                        Spacer(modifier = Modifier.width(40.dp))
                        Text(text = "Get Current Location", Modifier.weight(2.5f))
                    }

                }

                Spacer(modifier = modifier.weight(.5f))

                Button(
                    onClick = {

                        navController.navigate(Pages.SignUpScreen.route)

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
                    Text(text = "Submit")
                }

                Spacer(modifier = modifier.weight(.5f))

            }
        }
    }


    dialogQueue
        .reversed()
        .forEach { permission ->
            PermissionDialog(
                permissionTextProvider = when (permission) {
                    Manifest.permission.ACCESS_COARSE_LOCATION -> LocationPermissionTextProvider()
                    Manifest.permission.ACCESS_FINE_LOCATION -> LocationPermissionTextProvider()
                    else -> return@forEach
                },
                isPermanentlyDeclined = !shouldShowRequestPermissionRationale(activity, permission),
                onDismiss = {
                    locationViewModel.dismissDialogue()
                },
                onOkClick = {
                    locationViewModel.dismissDialogue()
                    permissionResultLauncher.launch(
                        arrayOf(permission)
                    )
                },
                onGoToAppSettings = {
                    Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.fromParts("packge", packageName, null)
                    )
                }
            )
        }


}


