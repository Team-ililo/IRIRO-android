package com.example.ililo.My.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.finishAffinity
import com.example.ililo.ApplicationClass.Companion.prefs
import com.example.ililo.Declare.view.DeclareActivity
import com.example.ililo.Login.view.SignInActivity
import com.example.ililo.My.model.LogOutRes
import com.example.ililo.My.model.UserProfileRes
import com.example.ililo.My.service.MyInterface
import com.example.ililo.My.service.MyService
import com.example.ililo.Notice.view.NoticeActivity
import com.example.ililo.databinding.FragmentMypageBinding

class MyPageFragment: Fragment(), MyInterface {
    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!
    private val userIdx = prefs.getLong("member_id",0L)

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

        MyService(this).tryGetUserProfile(userIdx)

        //공지사항 이동
        binding.tvNotice.setOnClickListener {
            val intent = Intent(context, NoticeActivity::class.java)
            startActivity(intent)
        }

        //신고하기 이동
        binding.tvGoWarn.setOnClickListener {
            val intent = Intent(context, DeclareActivity::class.java)
            startActivity(intent)
        }

        //로그아웃
        binding.tvLogout.setOnClickListener {
            MyService(this).tryGetLogOut()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onGetLogOutSuccess(response: LogOutRes) {
        prefs.edit().clear().apply()
        val intent = Intent(context, SignInActivity::class.java)
        finishAffinity(context as Activity)        // 스택에 쌓인 액티비티 비우기
        startActivity(intent)
    }

    override fun onGetLogOutFailure(message: String) {
        Log.d("onGetLogOutFailure", "failure")
    }

    override fun onGetUserProfileSuccess(response: UserProfileRes) {
        val res = response.data

        binding.tvUserEmail.text = res.email
        binding.tvUserCarNum.text = res.vehicle_number
        binding.tvUserAddress.text = res.address
        binding.tvUserWarn.text = res.number_of_complaints.toString() + "회"

        prefs.edit().putString("vehicle_number",res.vehicle_number)
        prefs.edit().apply()

    }

    override fun onGetUserProrileFailure(message: String) {
        Log.d("onGetUserProrileFailure", "failure")
    }
}