package com.example.sih2024.screens

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sih2024.presentation.customerHome.CustomerHomeScreen
import com.example.sih2024.models.User
import com.example.sih2024.presentation.LocationScreen
import com.example.sih2024.presentation.LoginScreen
import com.example.sih2024.presentation.OTPScreen
import com.example.sih2024.presentation.PhoneNumberScreen
import com.example.sih2024.presentation.SignUpMethodsScreen
import com.example.sih2024.presentation.SignUpScreen
import com.example.sih2024.presentation.customer.customerExplore.CategoryItemsScreen
import com.example.sih2024.presentation.customer.customerExplore.ExploreScreen
import com.example.sih2024.presentation.customer.customercart.MyCartScreen
import com.example.sih2024.presentation.customer.cutomerHome.Collections.SeeAllScreen
import com.example.sih2024.presentation.onBordingScreen
import com.example.sih2024.screens.pages.Pages
import com.example.sih2024.viewModels.AuthViewModel
import com.example.sih2024.viewModels.CustomerHomeScreenViewModel
import com.example.sih2024.viewModels.LocationViewModel
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel,
    locationViewModel: LocationViewModel,
    customerHomeScreenViewModel: CustomerHomeScreenViewModel,
    activity: Activity
) {
    val navController = rememberNavController()

    var showHomeScreen by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        showHomeScreen = viewModel.isUserLoggedIn()
        if (showHomeScreen) {
            val currentUser = viewModel.myAuth.currentUser
            val firestoreReference = viewModel.firestoreReference
            val email = currentUser?.email
            if (email != null) {
                try {
                    val collections = listOf("Customers", "Farmers", "Agents")
                    var userData: User? = null
                    var found = false

                    for (collectionName in collections) {
                        val documentSnapshot =
                            firestoreReference.collection(collectionName).document(email).get()
                                .await()
                        if (documentSnapshot.exists()) {
                            userData = documentSnapshot.toObject<User>()
                            found = true
                            break
                        }
                    }

                    if (found && userData != null) {
                        val latitude = userData.location.latitude
                        val longitude = userData.location.longitude
                        val userAddress = userData.address
                        val userType = userData.userType
                        val name = userData.name
                        val phoneNumber = userData.phoneNumber
                        val district = userData.district
                        val state = userData.state
                        viewModel.setLocationDetails(latitude, longitude)
                        viewModel.setAddress(userAddress)
                        viewModel.setDistrictAndState(district, state)
                        viewModel.setUserType(userType)
                        viewModel.getPhoneNumber(phoneNumber)
                        viewModel.getName(name)
                        viewModel.getEmail(email)
                        Log.d("data", userData.userType + userData.address)
                    } else {
                        Log.d("Navigation", "Data not found in any collection")
                    }

                } catch (e: FirebaseFirestoreException) {
                    Log.d("Navigation", "Firestore exception: ${e.message}")
                }
            }
        }
    }


    NavHost(
        navController = navController,
        startDestination = if (showHomeScreen && viewModel.userType.value == "Farmer") {
            Pages.FarmerHomeScreen.route
        } else if (showHomeScreen && viewModel.userType.value == "Customer") {
            Pages.CustomerHomeScreen.route
        } else if (showHomeScreen && viewModel.userType.value == "Agent") {
            Pages.AgentHomeScreen.route
        } else {
            Pages.onBoardScreen.route
        }


    ) {

        composable(route = Pages.onBoardScreen.route) {
            onBordingScreen(navController = navController)
        }

        composable(route = Pages.signUpMethodScreen.route) {
            SignUpMethodsScreen(navController)
        }

        composable(route = Pages.PhoneNumberScreen.route) {
            PhoneNumberScreen(
                viewModel = viewModel,
                navController = navController,
                activity = activity
            )
        }

        composable(route = Pages.OTPScreen.route) {
            OTPScreen(navController = navController, viewModel = viewModel, activity = activity)
        }

        composable(route = Pages.LocationScreen.route) {
            LocationScreen(
                authViewModel = viewModel,
                locationViewModel = locationViewModel,
                activity = activity,
                navController = navController
            )
        }

        composable(route = Pages.SignUpScreen.route) {
            SignUpScreen(
                authViewModel = viewModel,
                context = activity,
                navController = navController
            )
        }

        composable(route = Pages.LoginScreen.route) {
            LoginScreen(
                navController = navController,
                authViewModel = viewModel,
                locationViewModel = locationViewModel,
                context = activity
            )
        }

        composable(route = Pages.AgentHomeScreen.route) {
            CustomerHomeScreen(viewModel, customerHomeScreenViewModel, navController)
            //TODO:Change
        }


//Customer composables routes acess
        composable(route = Pages.CustomerHomeScreen.route) {
            CustomerHomeScreen(viewModel, customerHomeScreenViewModel, navController)
        }
        composable(route = Pages.CustomerCategoryScreen.route) {
            ExploreScreen(
                navController = navController,
                customerHomeScreenViewModel = customerHomeScreenViewModel,
                context = activity
            )
        }




        composable(route = Pages.FarmerHomeScreen.route) {
            CustomerHomeScreen(viewModel, customerHomeScreenViewModel, navController)
            //TODO:Change
        }

        composable(
            route = Pages.CategoryItemsScreen.route + "/{category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                    nullable = false
                }
            )

        ) {
            it.arguments?.getString("category")?.let { it1 ->
                CategoryItemsScreen(
                    navController = navController,
                    customerHomeScreenViewModel = customerHomeScreenViewModel,
                    category = it1,
                    context = activity
                )
            }
        }


        composable(
            route = Pages.SeeAllScreen.route + "/{offer}",
            arguments = listOf(
                navArgument("offer") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) {

            it.arguments?.getString("offer")?.let { it1 ->

                SeeAllScreen(
                    offerType = it1,
                    navController = navController,
                    customerHomeScreenViewModel = customerHomeScreenViewModel
                )

            }

        }
        
        composable(route = Pages.SignOut.route){
            SignoutScreen(authViewModel = viewModel , navController = navController , customerHomeScreenViewModel = customerHomeScreenViewModel)
        }


        composable(route = Pages.CartScreen.route){
            MyCartScreen(
                modifier = Modifier,
                authViewModel = viewModel,
                customerHomeScreenViewModel = customerHomeScreenViewModel,
                navController = navController,
                context = activity
            )
        }
    }


}


