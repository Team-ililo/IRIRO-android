package com.example.ililo.Home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ililo.Declare.MemberDeclareListActivity
import com.example.ililo.NoticeActivity
import com.example.ililo.databinding.FragmentHomeBinding

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
            val intent = Intent(context, NoticeActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoNotice.setOnClickListener {
            val intent = Intent(context, NoticeActivity::class.java)
            startActivity(intent)
        }

        binding.layoutNotice.setOnClickListener {
            val intent = Intent(context, NoticeActivity::class.java)
            startActivity(intent)
        }

        //신고내역 이동
        binding.btnDeclareMember.setOnClickListener {
            val intent = Intent(context, MemberDeclareListActivity::class.java)
            startActivity(intent)
        }

    }
}