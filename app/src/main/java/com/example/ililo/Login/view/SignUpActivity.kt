package com.example.ililo.Login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.ililo.ApplicationClass.Companion.prefs
import com.example.ililo.Login.model.SignUpRes
import com.example.ililo.Login.service.SignUpInterface
import com.example.ililo.Login.service.SignUpService
import com.example.ililo.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity(), SignUpInterface {
    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    private lateinit var getResultText: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 회원가입 실패
        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val mString = result.data?.getStringExtra("sign-up-fail")
                Toast.makeText(this, mString, Toast.LENGTH_SHORT).show()
            }
        }

        //회원가입 완료 시 로그인 이동
        binding.btnDone.setOnClickListener {
            val name = binding.tvSignupNameText.text.toString()
            val phone_number = binding.tvSignupPhoneText.text.toString()
            val vehicle_number = binding.tvSignupCarNumText.text.toString()
            val vehicle_model = binding.tvSignupCarTypeText.text.toString()
            val vehicle_color = binding.tvSignupCarColorText.text.toString()
            val apartment_name = binding.tvSignupApartmentText.text.toString()
            val address = binding.tvSignupAddressText.text.toString()
            val email = binding.tvSignupEmailText.text.toString()
            val password = binding.tvSignupPasswordText.text.toString()
            val pw_check = binding.tvSignupPwCheckText.text.toString()
            val device_id = binding.tvSignupDeviceText.text.toString().toInt()

            val editor = prefs.edit()

            if ((name != null) && (phone_number != null)
                && (vehicle_number != null) && (vehicle_model != null) && (vehicle_color != null)
                && (apartment_name != null) && (address != null)
                && (email != null) && (password != null) && (pw_check != null) && (device_id != null)) {

                editor.putString("name", name)
                editor.putString("email", email)
                editor.putString("password", password)
                editor.putInt("device_id", device_id)
                editor.apply()

                SignUpService(this).tryPostSignUp(name, phone_number,
                    vehicle_number, vehicle_model, vehicle_color,
                    apartment_name, address,
                    email, password, pw_check, device_id)
            }
        }
    }

    override fun onPostSignUpSuccess(response: SignUpRes) {
        val intent = Intent(this,SignInActivity::class.java)
        finishAffinity()        // 스택에 쌓인 액티비티 비우기
        startActivity(intent)
    }

    override fun onPostSignUpFailure(message: String) {
        Log.d("SignUp","실패")
    }
}