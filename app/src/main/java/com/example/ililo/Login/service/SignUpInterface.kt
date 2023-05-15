package com.example.ililo.Login.service

import com.example.ililo.Login.model.SignUpRes

interface SignUpInterface {
    fun onPostSignUpSuccess(response: SignUpRes)
    fun onPostSignUpFailure(message: String)
}