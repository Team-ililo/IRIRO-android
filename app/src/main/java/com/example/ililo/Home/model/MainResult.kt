package com.example.ililo.Home.model

data class MainResult (
    val exitTime: String,
    val remainingTime: String,
    val isLongTermParking: Boolean,
    val apartmentName: String,
    val address: String
)