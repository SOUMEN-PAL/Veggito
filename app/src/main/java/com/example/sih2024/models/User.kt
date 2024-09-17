package com.example.sih2024.models

import com.google.firebase.firestore.GeoPoint

data class User(
    val name : String,
    val email: String,
    val phoneNumber: String,
    var userType: String,
    var location : GeoPoint,
    var address : String,
    var state : String,
    var district : String
){
    // No-argument constructor for Firestore
    constructor() : this("", "", "", "", GeoPoint(0.0, 0.0), "" , "" , "")
}
