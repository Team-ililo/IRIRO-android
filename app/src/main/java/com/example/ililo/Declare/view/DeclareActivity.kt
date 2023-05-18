package com.example.ililo.Declare.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ililo.Declare.model.DeclareReq
import com.example.ililo.Declare.model.DeclareRes
import com.example.ililo.Declare.service.DeclareInterface
import com.example.ililo.Declare.service.DeclareService
import com.example.ililo.databinding.ActivityDeclareBinding

class DeclareActivity : AppCompatActivity(), DeclareInterface {
    private val binding: ActivityDeclareBinding by lazy {
        ActivityDeclareBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //뒤로가기
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnDone.setOnClickListener {
            val car_num = binding.tvDeclareNameText.text.toString()
            val reason = binding.tvDeclareReason.text.toString()

            DeclareService(this).tryPostDeclare(car_num, reason)
        }
    }

    override fun onGetDeclareListSuccess(response: DeclareRes) {
        Log.d("신고하기","성공")
        finish()
    }

    override fun onGetDeclareListFailure(message: String) {
        Log.d("신고하기","실패")
    }


}