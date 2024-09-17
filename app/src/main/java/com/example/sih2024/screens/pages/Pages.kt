package com.example.sih2024.screens.pages

sealed class Pages(val route : String) {
    data object onBoardScreen : Pages("onBoardScreen")
    data object signUpMethodScreen : Pages("signUpMethods")
    data object PhoneNumberScreen : Pages("phoneNumberScreen")
    data object OTPScreen : Pages("otpNumberScreen")
    data object LocationScreen: Pages("locationScreen")
    data object SignUpScreen : Pages("signUpScreen")
    data object LoginScreen : Pages("loginScreen")

    //customer Screens
    data object CustomerHomeScreen : Pages("customerHomeScreen")
    data object CustomerCategoryScreen : Pages("customerCategoryScreen")
    data object CategoryItemsScreen : Pages("categoryItemsScreen")



    //farmer Screens
    data object FarmerHomeScreen : Pages("farmerHomeScreen")



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