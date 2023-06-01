package com.example.ililo.Login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.ililo.ApplicationClass.Companion.prefs
import com.example.ililo.Login.model.SignInRes
import com.example.ililo.Login.service.SignInInterface
import com.example.ililo.Login.service.SignInService
import com.example.ililo.MainActivity
import com.example.ililo.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity(), SignInInterface {
    private val binding: ActivitySignInBinding by lazy {
        ActivitySignInBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //회원가입 이동
        binding.tvSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        //로그인 시 메인페이지 이동
        binding.btnDone.setOnClickListener {
            val id:String = binding.tvEmail.text.toString()
            val pw:String = binding.tvPassword.text.toString()

            if (id.isBlank() || pw.isBlank()){
                Toast.makeText(this, "입력되지 않은 칸이 존재합니다.", Toast.LENGTH_SHORT).show()
            } else {
                SignInService(this).tryPostSignIn(id, pw)
            }
        }
    }

    override fun onPostSignUpSuccess(response: SignInRes) {

        val member_id = response.data.member_id
        val apartment_name = response.data.apartment_name
        val device_id = response.data.device_id
        val vehicle_id = response.data.vehicle_id

        val intent = Intent(this, MainActivity::class.java)
        finishAffinity()        // 스택에 쌓인 액티비티 비우기
        startActivity(intent)

        val editor = prefs.edit()
        editor.putLong("member_id", member_id)
        editor.putString("apartment_name", apartment_name)
        editor.putLong("device_id", device_id)
        editor.putLong("vehicle_id", vehicle_id)
        editor.apply()
    }

    override fun onPostSignUpFailure(message: String) {
        Log.d("onPostSignUpFailure", "failure")
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}