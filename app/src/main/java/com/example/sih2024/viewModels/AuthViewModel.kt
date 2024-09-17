package com.example.sih2024.viewModels

import android.app.Activity
import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sih2024.models.User
import com.google.firebase.FirebaseException
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit

class AuthViewModel : ViewModel() {

    //User details stored initially
    private var _name = mutableStateOf("")
    private var _userType = mutableStateOf("")
    private var _email = mutableStateOf("")
    private var _password = mutableStateOf("")
    private var _phoneNumber = mutableStateOf("")
    private var _otp = mutableStateOf("")
    private var _isVerificationCompleted = mutableStateOf(false)
    private var _signInSuccessful = mutableStateOf(false)
    private var _latitude = mutableDoubleStateOf(0.0)
    private var _longitude = mutableDoubleStateOf(0.0)
    private var _location = mutableStateOf("")
    private var _district = mutableStateOf("")
    private var _state = mutableStateOf("")

    //Firebase setup
    private var _myAuth : FirebaseAuth = Firebase.auth
    private var verificationID = mutableStateOf("")
    lateinit var credential: PhoneAuthCredential

    //Firebase Firestore
    private var firestore : FirebaseFirestore = Firebase.firestore
    val firestoreReference = firestore

    //Exposing the values
    val name = _name
    val userType = _userType
    val email = _email
    val password = _password
    val phoneNumber = _phoneNumber
    val otp = _otp
    val isVerificationCompleted = _isVerificationCompleted
    val signInSuccessful = _signInSuccessful
    val myAuth = _myAuth

    val longitude = _longitude
    val latitude = _latitude
    val location = _location
    val state = _state
    val district = _district

    fun setLocationDetails(lat : Double , long : Double){
        _latitude.doubleValue = lat
        _longitude.doubleValue = long
    }

    fun setDistrictAndState(district : String , state : String){
        _district.value = district
        _state.value = state
    }

    fun setAddress(address : String){
        _location.value = address
    }

    fun setUserType(userType : String){
        _userType.value = userType
    }

    fun getName(name : String){
        _name.value = name
    }

    //Checking if already logged in
    suspend fun isUserLoggedIn(): Boolean {
        return try {
            val isLoggedIn = _myAuth.currentUser != null
            if (isLoggedIn) {
                _email.value = _myAuth.currentUser?.email.toString()
                _phoneNumber.value = _myAuth.currentUser?.phoneNumber.toString()
            }
            isLoggedIn
        } catch (e: Exception) {
            Log.e("MainViewModel", "Error checking login status: ${e.message}", e)
            false
        }
    }

    //Phone Auth
    fun getPhoneNumber(number : String){
        _phoneNumber.value = number
    }

    fun getOtp(otp : String){
        _otp.value = otp
    }

    fun sendOtp(activity: Activity){
        val number = "+91${_phoneNumber.value}"
        sendVerification(number , activity)

    }

    fun sendVerification(number : String , activity: Activity){
        val options = PhoneAuthOptions
            .newBuilder(_myAuth)
            .setPhoneNumber(number)
            .setTimeout(60L , TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(verificationCallBack)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        _myAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                _signInSuccessful.value = task.isSuccessful
            }
    }

    //callback function
    val verificationCallBack : OnVerificationStateChangedCallbacks = object : OnVerificationStateChangedCallbacks(){
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {

            _isVerificationCompleted.value = true
            signInWithPhoneAuthCredential(credential)

        }

        override fun onVerificationFailed(e: FirebaseException) {
            Log.e("FirebaseVerification", "Verification failed: ${e.message}", e)
        }

        override fun onCodeSent(s: String, p1: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(s, p1)
            verificationID.value = s

        }
    }

    fun verifyOTP(onSuccess: (Boolean) -> Unit) {
        try {
            credential = PhoneAuthProvider.getCredential(verificationID.value, otp.value)
            _myAuth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onSuccess(true)
                    } else {
                        Log.e("MainViewModel", "OTP verification failed: ${task.exception?.message}", task.exception)
                        onSuccess(false)
                    }
                }
        } catch (e: Exception) {
            Log.e("MainViewModel", "Error verifying OTP: ${e.message}", e)
            onSuccess(false)
        }
    }

    //phone auth done

    //Email auth

    fun getEmail(email:String){
        _email.value = email
    }
    fun getPassword(password : String){
        _password.value = password
    }


    fun linkPhoneCredential(userData: User, onResult: (Boolean) -> Unit) {
        val user = _myAuth.currentUser
        val credential_email = EmailAuthProvider.getCredential(email.value , password.value)
        user?.linkWithCredential(credential_email)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if(userData.userType == "Farmer"){
                        viewModelScope.launch {
                            try {
                                SavefarmerToFireBase(userData = userData)
                                onResult(true)
                            }catch (e: Exception) {
                                Log.e("MainViewModel", "Error saving farmer to Firestore: ${e.message}", e)
                                onResult(false)
                            }
                        }

                    }
                    else if(userData.userType == "Customer") {
                        viewModelScope.launch {
                            try {
                                SaveCustomerToFireBase(userData = userData)
                                onResult(true)
                            } catch (e: Exception) {
                                Log.e(
                                    "MainViewModel",
                                    "Error saving user to Firestore: ${e.message}",
                                    e
                                )
                                onResult(false)
                            }
                        }
                    }
                    else if(userData.userType == "Agent"){
                        viewModelScope.launch {
                            try {
                                SaveAgentToFireBase(userData = userData)
                                onResult(true)
                            } catch (e: Exception) {
                                Log.e(
                                    "MainViewModel",
                                    "Error saving user to Firestore: ${e.message}",
                                    e
                                )
                                onResult(false)
                            }
                        }
                    }
                } else {
                    Log.e(
                        "MainViewModel",
                        "Linking phone credential failed: ${task.exception?.message}",
                        task.exception
                    )
                    onResult(false)
                }
            }
    }

    private suspend fun SaveCustomerToFireBase(userData: User) {
        try {
            firestore.collection("Customers").document(userData.email).set(userData).await()
            Log.d("MainViewModel", "User saved to Firestore")
        } catch (e: Exception) {
            Log.e("MainViewModel", "Error saving user to Firestore: ${e.message}", e)
        }
    }
    private suspend fun SavefarmerToFireBase(userData: User) {
        try {
            firestore.collection("Farmers").document(userData.email).set(userData).await()
            Log.d("MainViewModel", "Farmer saved to Firestore")
        } catch (e: Exception) {
            Log.e("MainViewModel", "Error saving farmer to Firestore: ${e.message}", e)
        }
    }
    private suspend fun SaveAgentToFireBase(userData: User) {
        try {
            firestore.collection("Agents").document(userData.email).set(userData).await()
            Log.d("MainViewModel", "Farmer saved to Firestore")
        } catch (e: Exception) {
            Log.e("MainViewModel", "Error saving farmer to Firestore: ${e.message}", e)
        }
    }

    fun isValidEmail(): Boolean {
        return email.value.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
    }

    fun login(onResult:(Boolean) -> Unit) {
        _myAuth.signInWithEmailAndPassword(email.value, password.value)
            .addOnCompleteListener { task ->
                onResult(task.isSuccessful)
            }
    }

    fun sendPasswordResetEmail(email:String) {
        viewModelScope.launch {
            try {
                _myAuth.sendPasswordResetEmail(email)
                    .addOnSuccessListener {
                        // Password reset email sent successfully
                        // Show success message to the user
                    }
                    .addOnFailureListener { exception ->
                        // Handle error(e.g., invalid email, network issue)
                        // Show error message to the user
                    }
            } catch (e: Exception) {
                // Handle exceptions
            }
        }
    }


    fun resetUserData() {
        _name.value = ""
        _userType.value = ""
        _email.value = ""
        _password.value = ""
        _phoneNumber.value = ""
        _otp.value = ""
        _latitude.value = 0.0
        _longitude.value = 0.0
        _location.value = ""
    }

    fun signOut() {
        resetUserData()
        _myAuth.signOut()
    }




    fun LoginUtil(modifier: Modifier = Modifier, locationViewModel: LocationViewModel, context: Context, onSuccess : (Boolean) ->Unit) {
        viewModelScope.launch {
            val currentUser = myAuth.currentUser
            val firestoreReference = firestoreReference
            val email = _email
            try {
                val collections = listOf("Customers", "Farmers", "Agents")
                var userData: User? = null
                var found = false

                for (collectionName in collections) {
                    val documentSnapshot =
                        firestoreReference.collection(collectionName)
                            .document(email.value).get().await()
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
                    val state = userData.state
                    val district = userData.district
                    setLocationDetails(latitude, longitude)
                    setAddress(userAddress)
                    setUserType(userType)
                    getPhoneNumber(phoneNumber)
                    getName(name)
                    getEmail(email.value)
                    Log.d("data", userData.userType + userData.address)
                    onSuccess(true)
                } else {
                    Log.d("Navigation", "Data not found in any collection")
                    onSuccess(false)
                }

            } catch (e: FirebaseFirestoreException) {
                Log.d("Navigation", "Firestore exception: ${e.message}")
                onSuccess(false)
            }
        }
    }


}