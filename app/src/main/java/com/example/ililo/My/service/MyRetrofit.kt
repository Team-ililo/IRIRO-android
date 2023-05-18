package com.example.ililo.My.service

import com.example.ililo.My.model.LogOutRes
import com.example.ililo.My.model.UserProfileRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MyRetrofit {
    @GET("members/logout")
    fun getLogOutRes() : Call<LogOutRes>

    @GET("members/{id}")
    fun getUserProfileRes(
        @Path("id") id: Long
    ) : Call<UserProfileRes>
}