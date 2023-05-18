package com.example.ililo.Login.service

import com.example.ililo.Login.model.SignInRes

interface SignInInterface {
    fun onPostSignUpSuccess(response: SignInRes)
    fun onPostSignUpFailure(message: String)
}