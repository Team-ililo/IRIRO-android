package com.example.ililo.Park

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ililo.databinding.ActivityDepartureInfoBinding

class DepartureInfoActivity : AppCompatActivity() {
    private val binding: ActivityDepartureInfoBinding by lazy {
        ActivityDepartureInfoBinding.inflate(layoutInflater)
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