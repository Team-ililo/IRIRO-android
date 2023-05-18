package com.example.ililo.Notice.model

import com.example.ililo.BaseResponse

data class NoticeRes(
    val data: ArrayList<NoticeList>
): BaseResponse()
