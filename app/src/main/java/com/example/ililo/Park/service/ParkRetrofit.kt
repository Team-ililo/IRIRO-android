package com.example.ililo.Park.service

import com.example.ililo.BaseResponse
import com.example.ililo.Park.model.RegisterReq
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ParkRetrofit {
    @POST("vehicle/departuretime/{id}")
    fun postRegisterTimeReq(
        @Path("id") id: Long,
        @Body Info: RegisterReq
    ) : Call<BaseResponse>
}