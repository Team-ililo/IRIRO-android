package com.example.ililo.Declare.service

import com.example.ililo.Declare.model.DeclareListRes
import com.example.ililo.Declare.model.DeclareReq
import com.example.ililo.Declare.model.DeclareRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DeclareRetrofit {
    @POST("members/complaints")
    fun postDeclareRes(
        @Body Info: DeclareReq
    ) : Call<DeclareRes>

    @GET("members/{id}/complaints")
    fun getDeclareListRes(
        @Path("id") id: Long
    ) : Call<DeclareListRes>


}