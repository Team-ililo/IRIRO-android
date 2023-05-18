package com.example.ililo.Declare.service

import com.example.ililo.Declare.model.DeclareReq
import com.example.ililo.Declare.model.DeclareRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface DeclareRetrofit {
    @POST("members/complaints")
    fun postDeclareRes(
        @Body Info: DeclareReq
    ) : Call<DeclareRes>
}