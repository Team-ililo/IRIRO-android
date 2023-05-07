package com.example.ililo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ililo.databinding.ActivityMemberDeclareListBinding

class MemberDeclareListActivity : AppCompatActivity() {
    private val binding: ActivityMemberDeclareListBinding by lazy {
        ActivityMemberDeclareListBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //뒤로가기
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}