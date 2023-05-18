package com.example.ililo.My.service

import com.example.ililo.My.model.LogOutRes
import com.example.ililo.My.model.UserProfileRes

interface MyInterface {
    fun onGetLogOutSuccess(response: LogOutRes)
    fun onGetLogOutFailure(message: String)

    fun onGetUserProfileSuccess(response: UserProfileRes)
    fun onGetUserProrileFailure(message: String)
}