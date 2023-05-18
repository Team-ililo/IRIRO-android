package com.example.ililo.Notice.service

import com.example.ililo.Notice.model.NoticeRes

interface NoticeListInterface {
    fun onGetNoticeListSuccess(response: NoticeRes)
    fun onGetNoticeListFailure(message: String)
}