package com.example.sih2024.screens.pages

sealed class Pages(val route : String) {
    data object onBoardScreen : Pages("onBoardScreen")
    data object signUpMethodScreen : Pages("signUpMethods")
    data object PhoneNumberScreen : Pages("phoneNumberScreen")
    data object OTPScreen : Pages("otpNumberScreen")
    data object LocationScreen: Pages("locationScreen")
    data object SignUpScreen : Pages("signUpScreen")
    data object LoginScreen : Pages("loginScreen")
    data object SignOut : Pages("signoutScreen")


    //customer Screens
    data object CustomerHomeScreen : Pages("customerHomeScreen")
    data object CustomerCategoryScreen : Pages("customerCategoryScreen")
    data object CategoryItemsScreen : Pages("categoryItemsScreen")
    data object SeeAllScreen : Pages("seeAllScreen")
    data object CartScreen : Pages("cartScreen")
    data object FavouriteScreen : Pages("favouriteScreen")
    data object CustomerAccountScreen : Pages("customer account")

    //farmer Screens
    data object FarmerHomeScreen : Pages("farmerHomeScreen")
    data object TransactionScreen : Pages("transactionScreen")
    data object ListProductFarmers : Pages("OrderScreen")
    data object ShipmentScreen : Pages("ShipmentScreen")
    data object FarmerAccountScreen : Pages("farmerAccountScreen")

    //Agent Screens
    data object AgentHomeScreen : Pages("agentHomeScreen")


    //WithArgs
    fun withArgs(vararg args: String):String{
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }
}