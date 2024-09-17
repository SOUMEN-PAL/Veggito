package com.example.sih2024.viewModels

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.type.LatLng
import java.io.IOException
import java.util.Locale

class LocationViewModel(private val context: Context , private val authViewModel: AuthViewModel): ViewModel() {
    val visiblePermissionDialogueQueue = mutableStateListOf<String>()

    //Location CLient
    private val _fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    fun dismissDialogue(){
        visiblePermissionDialogueQueue.removeFirst()
    }

    fun onPermissionResult(
        permission: String,
        isGranted : Boolean
    ){
        if(!isGranted && !visiblePermissionDialogueQueue.contains(permission)){
            visiblePermissionDialogueQueue.add(permission)
        }
    }

//Location functions
    fun hasLocationPermission(): Boolean{ // A helper function which can
        // be used anywhere to check if we have location permission or not

        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                &&
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }



    @SuppressLint("MissingPermission")
    fun requestLocationUpdates(onSuccess : (Boolean)->Unit){
        onSuccess(false)
        val locationCallback = object : LocationCallback(){  // creating an object of LocationCallback to ovveride its function
            // which contains the location latitude and longitude) of the user
            override fun onLocationResult(LocationResult: LocationResult) {
                super.onLocationResult(LocationResult)
                if(LocationResult.lastLocation != null) {   // get the last location of the user using the overriden object
                    // and update it inside the LocationData, let is used to unpack the Locationresult.
                    var lattitude = LocationResult.lastLocation!!.latitude
                    var longitude = LocationResult.lastLocation!!.longitude
                    authViewModel.setLocationDetails(lattitude , longitude) //  update the location to the current one
                    onSuccess(true)
                }else{
                    onSuccess(false)
                }
            }
        }
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            1000
        ).build()

        _fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())

    }




//    fun reverseGeoCodeLocation(): String {
//        val latitude = authViewModel.latitude
//        val longitude = authViewModel.longitude
//
//        if (latitude.doubleValue == 0.0 || longitude.doubleValue == 0.0) {
//            return "Location not available"
//        }
//
//        return try {
//            val geocoder = Geocoder(context, Locale.getDefault())
//            val addresses = geocoder.getFromLocation(latitude.doubleValue, longitude.doubleValue, 1)
//            if (addresses?.isNotEmpty() == true) {
//                val address = addresses[0].getAddressLine(0)
//                authViewModel.location.value = address
//                address
//            } else {
//                "Address not found"
//            }
//        } catch (e: IOException) {
//            "Error: ${e.message}"
//        }
//    }


//for android 9+

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun reverseGeoCodeLocation(): String {
        val latitude = authViewModel.latitude
        val longitude = authViewModel.longitude

        if (latitude.doubleValue == 0.0 || longitude.doubleValue == 0.0) {
            return "Location not available"
        }

        return try {
            val geocoder = Geocoder(context, Locale.getDefault())
            var address = "Address not found"
            geocoder.getFromLocation(
                latitude.doubleValue,
                longitude.doubleValue,
                1,
                object : Geocoder.GeocodeListener {
                    override fun onGeocode(addresses: MutableList<Address>) {
                        if (addresses.isNotEmpty()) {
                            address = addresses[0].getAddressLine(0)
                            val district = addresses[0].subAdminArea   // District or locality
                            val state = addresses[0].adminArea
                            authViewModel.setDistrictAndState(district , state)
                            authViewModel.location.value = address
                        }
                    }

                    override fun onError(errorMessage: String?) {
                        address = "Error: $errorMessage"
                    }
                }
            )
            address
        } catch (e: IOException) {
            "Error: ${e.message}"
        }
    }


}