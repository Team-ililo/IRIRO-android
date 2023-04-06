package com.example.ililo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ililo.databinding.ActivityDeclareBinding
import com.example.ililo.databinding.ActivityNoticeBinding

class Declare : AppCompatActivity() {
    private val binding: ActivityDeclareBinding by lazy {
        ActivityDeclareBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}