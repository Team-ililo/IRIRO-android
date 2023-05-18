package com.example.ililo.Notice.service

import com.example.ililo.Notice.model.NoticeRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NoticeRetrofit {
    @GET("apartment/{id}")
    fun getNoticeRes(
        @Path("id") id: Long
    ) : Call<NoticeRes>
}