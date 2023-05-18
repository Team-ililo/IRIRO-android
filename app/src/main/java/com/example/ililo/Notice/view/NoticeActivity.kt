package com.example.ililo.Notice.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ililo.Notice.model.NoticeList
import com.example.ililo.Notice.model.NoticeRes
import com.example.ililo.Notice.service.NoticeListInterface
import com.example.ililo.Notice.service.NoticeService
import com.example.ililo.Notice.view.adapter.NoticeListRVAdapter
import com.example.ililo.databinding.ActivityNoticeBinding

class NoticeActivity : AppCompatActivity(), NoticeListInterface {
    private val binding: ActivityNoticeBinding by lazy {
        ActivityNoticeBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        NoticeService(this).tryGetNoticeList(1)

        //뒤로가기
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onGetNoticeListSuccess(response: NoticeRes) {

        Log.d("공지사항", "success")

        val res = response.data

        val noticeList: ArrayList<NoticeList> = arrayListOf()
        val listAdapter = NoticeListRVAdapter(noticeList, 1)

        binding.rvNotice.adapter = listAdapter
        binding.rvNotice.layoutManager = LinearLayoutManager(this)

        noticeList.addAll(res)
    }

    override fun onGetNoticeListFailure(message: String) {
        Log.d("공지사항", "failure")
    }
}