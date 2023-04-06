package com.example.ililo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ililo.databinding.FragmentHomeBinding
import com.example.ililo.databinding.FragmentMypageBinding

class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //공지사항 이동
        binding.tvNotice.setOnClickListener {
            val intent = Intent(context, Notice::class.java)
            startActivity(intent)
        }

        binding.btnGoNotice.setOnClickListener {
            val intent = Intent(context, Notice::class.java)
            startActivity(intent)
        }

        binding.layoutNotice.setOnClickListener {
            val intent = Intent(context, Notice::class.java)
            startActivity(intent)
        }

    }
}