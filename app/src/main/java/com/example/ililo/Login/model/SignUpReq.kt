package com.example.ililo.Login.model

data class SignUpReq(
    val name: String,
    val phone_number: String,
    val vehicle_number: String,
    val vehicle_model: String,
    val vehicle_color: String,
    val apartment_name: String,
    val address: String,
    val email: String,
    val password: String,
    val pw_check: String,
    val device_id: Int,
)
