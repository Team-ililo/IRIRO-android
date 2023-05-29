package com.example.ililo.Park.model

data class NearVehicleList(
    val vehicle_number: String,
    val model: String,
    val color: String,
    val exitTime: String,
    val isLongTermParking: Boolean,
    val isSatisfied: Boolean
)
