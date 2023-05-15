package com.example.ililo.Login.service

import com.example.ililo.Login.model.SignUpReq
import com.example.ililo.Login.model.SignUpRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRetrofit {
    @POST("members")
    fun postSignUpReq(
        @Body Info: SignUpReq
    ) : Call<SignUpRes>
}