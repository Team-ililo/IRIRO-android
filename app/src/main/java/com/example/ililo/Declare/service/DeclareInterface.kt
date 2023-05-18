package com.example.ililo.Declare.service

import com.example.ililo.Declare.model.DeclareRes

interface DeclareInterface {
    fun onGetDeclareListSuccess(response: DeclareRes)
    fun onGetDeclareListFailure(message: String)
}