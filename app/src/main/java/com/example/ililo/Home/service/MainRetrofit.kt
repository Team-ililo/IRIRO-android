package com.example.ililo.Home.service

import com.example.ililo.Home.model.MainRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MainRetrofit {
    @GET("vehicle/departuretime/{id}")
    fun getMainRes(
        @Path("id") id: Long
    ) : Call<MainRes>
}