package com.example.ililo.Login.model

data class SignUpResult(
    val member_id: Long,
    val name: String,
    val phone_number: String,
    val email: String,
    val password: String,
    val address: String,
    val number_of_complaints: Int
)
