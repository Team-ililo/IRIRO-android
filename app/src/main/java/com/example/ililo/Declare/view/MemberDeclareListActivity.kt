package com.example.ililo.Declare.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ililo.ApplicationClass.Companion.prefs
import com.example.ililo.Declare.model.DeclareListRes
import com.example.ililo.Declare.model.DeclareListResult
import com.example.ililo.Declare.model.DeclareRes
import com.example.ililo.Declare.model.Reports
import com.example.ililo.Declare.service.DeclareInterface
import com.example.ililo.Declare.service.DeclareService
import com.example.ililo.Declare.view.adapter.DeclareListRVAdapter
import com.example.ililo.databinding.ActivityMemberDeclareListBinding

class MemberDeclareListActivity : AppCompatActivity(), DeclareInterface {
    private val binding: ActivityMemberDeclareListBinding by lazy {
        ActivityMemberDeclareListBinding.inflate(layoutInflater)
    }
    private val id = prefs.getLong("member_id",0L)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        DeclareService(this).tryGetDeclareList(id)

        //뒤로가기
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onPostDeclareListSuccess(response: DeclareRes) {
        TODO("Not yet implemented")
    }

    override fun onPostDeclareListFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetDeclareListSuccess(response: DeclareListRes) {
        Log.d("신고내역", "success")

        val res = response.data
        val declareList: ArrayList<Reports> = arrayListOf()
        val listAdapter = DeclareListRVAdapter(declareList)

        binding.rvDeclareReason.adapter = listAdapter
        binding.rvDeclareReason.layoutManager = LinearLayoutManager(this)

        declareList.addAll(res.reports)

        binding.tvDeclareTime.text = res.number_of_complaints.toString() +"회"

        if (res.number_of_complaints == 0L) {
            binding.tvDeclareError.text = "신고내역이 존재하지 않습니다."
        }
    }

    override fun onGetDeclareListFailure(message: String) {
        Log.d("신고내역", "failure")
    }
}