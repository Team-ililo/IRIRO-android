package com.example.ililo.Notice.service

import com.example.ililo.Notice.model.NoticeRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NoticeRetrofit {
    @GET("apartment/{apartmentName}")
    fun getNoticeRes(
        @Path("apartmentName") apartmentName: String
    ) : Call<NoticeRes>
}