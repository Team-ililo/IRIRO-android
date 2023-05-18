package com.example.ililo.Login.service

import android.util.Log
import com.example.ililo.ApplicationClass.Companion.prefs
import com.example.ililo.ApplicationClass.Companion.sRetrofit
import com.example.ililo.Login.model.SignInReq
import com.example.ililo.Login.model.SignInRes
import com.example.ililo.Login.model.SignUpReq
import com.example.ililo.Login.model.SignUpRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.StreamHandler
import kotlin.math.sign

class SignUpService(val signUpInterface: SignUpInterface) {
    private val retrofit: LoginRetrofit = sRetrofit.create(LoginRetrofit::class.java)

    fun tryPostSignUp(
        name: String, phone_number: String,
        vehicle_number: String, vehicle_model: String, vehicle_color: String,
        apartment_name: String, address: String,
        email: String, password: String, pw_check: String, device_id: Int) {

        if (name!!.isBlank() || email!!.isBlank() || password!!.isBlank())
            Log.d("SignUpLocal","prefs error")
        else {
            retrofit.postSignUpReq(SignUpReq(name, phone_number,
                vehicle_number, vehicle_model, vehicle_color,
                apartment_name, address,
                email, password, pw_check, device_id)).enqueue((object : Callback<SignUpRes>{

                override fun onResponse(call: Call<SignUpRes>, response: Response<SignUpRes>) {
                    if (response.isSuccessful) {
                        signUpInterface.onPostSignUpSuccess(response.body() as SignUpRes)
                        Log.d("SignUp", "success")
                    } else {
                        Log.d("SignUp", "failure")
                    }
                }
                override fun onFailure(call: Call<SignUpRes>, t: Throwable) {
                    Log.d("tryPostSignUp", t.message!!)
                    t.printStackTrace()
                    signUpInterface.onPostSignUpFailure(t.message ?: "통신오류")
                }
                }))
        }
    }
}

class SignInService(val signInInterface: SignInInterface) {
    private val retrofit: LoginRetrofit = sRetrofit.create(LoginRetrofit::class.java)

    fun tryPostSignIn(email: String, password: String) {
        retrofit.postSignInReq(SignInReq(email, password)).enqueue((object : Callback<SignInRes>{
            override fun onResponse(call: Call<SignInRes>, response: Response<SignInRes>) {
                if(response.isSuccessful) {
                    signInInterface.onPostSignUpSuccess(response.body() as SignInRes)
                    Log.d("SignIn", "success")
                } else {
                    Log.d("SignIn", "failure")
                }
            }
            override fun onFailure(call: Call<SignInRes>, t: Throwable) {
                Log.d("tryPostSignIn", t.message!!)
                t.printStackTrace()
                signInInterface.onPostSignUpFailure(t.message ?: "통신오류")
            }
        }))
    }
}