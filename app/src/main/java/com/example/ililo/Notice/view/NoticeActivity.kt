package com.example.ililo.Notice.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ililo.ApplicationClass
import com.example.ililo.ApplicationClass.Companion.prefs
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
    private val name = prefs.getString("apartment_name","푸르지오")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (name != null) {
            NoticeService(this).tryGetNoticeList(name)
        }

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
        binding.tvNoticeError.text = message
        Log.d("공지사항", "failure")
    }
}