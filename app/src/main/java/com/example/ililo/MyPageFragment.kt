package com.example.ililo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ililo.databinding.FragmentMypageBinding

class MyPageFragment: Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //공지사항 이동
        binding.tvNotice.setOnClickListener {
            val intent = Intent(context, Notice::class.java)
            startActivity(intent)
        }

        //신고하기 이동
        binding.tvGoWarn.setOnClickListener {
            val intent = Intent(context, Declare::class.java)
            startActivity(intent)
        }
    }
}