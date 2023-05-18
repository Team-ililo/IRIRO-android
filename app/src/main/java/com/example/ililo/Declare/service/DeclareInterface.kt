package com.example.ililo.Declare.service

import com.example.ililo.Declare.model.DeclareListRes
import com.example.ililo.Declare.model.DeclareRes

interface DeclareInterface {
    fun onPostDeclareListSuccess(response: DeclareRes)
    fun onPostDeclareListFailure(message: String)

    fun onGetDeclareListSuccess(response: DeclareListRes)
    fun onGetDeclareListFailure(message: String)
}