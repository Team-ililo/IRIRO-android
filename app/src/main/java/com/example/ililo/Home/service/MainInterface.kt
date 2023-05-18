package com.example.ililo.Home.service

import com.example.ililo.Home.model.MainRes

interface MainInterface {
    fun onGetMainSuccess(response: MainRes)
    fun onGetMainFailure(message: String)
}