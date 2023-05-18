package com.example.ililo.My.service

import android.util.Log
import com.example.ililo.ApplicationClass
import com.example.ililo.My.model.LogOutRes
import com.example.ililo.My.model.UserProfileRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyService(val myInterface: MyInterface) {
    private val retrofit: MyRetrofit = ApplicationClass.sRetrofit.create(MyRetrofit::class.java)

    fun tryGetLogOut(){
        retrofit.getLogOutRes().enqueue((object : Callback<LogOutRes>{
            override fun onResponse(call: Call<LogOutRes>, response: Response<LogOutRes>) {
                if (response.isSuccessful) {
                    myInterface.onGetLogOutSuccess(response.body() as LogOutRes)
                    Log.d("Logout", "success")
                } else {
                    Log.d("Logout", "failure")
                }
            }
            override fun onFailure(call: Call<LogOutRes>, t: Throwable) {
                Log.d("tryGetLogOut", t.message!!)
                t.printStackTrace()
                myInterface.onGetLogOutFailure(t.message ?: "통신오류")
            }
        }))
    }

    fun tryGetUserProfile(id: Long) {
        retrofit.getUserProfileRes(id).enqueue((object : Callback<UserProfileRes>{
            override fun onResponse(call: Call<UserProfileRes>,
                response: Response<UserProfileRes>
            ) {
                if(response.isSuccessful) {
                    myInterface.onGetUserProfileSuccess(response.body() as UserProfileRes)
                    Log.d("getProfile", "success")
                } else {
                    Log.d("getProfile", "failure")
                }
            }
            override fun onFailure(call: Call<UserProfileRes>, t: Throwable) {
                Log.d("tryGetUserProfile", t.message!!)
                t.printStackTrace()
                myInterface.onGetUserProrileFailure(t.message ?: "통신오류")
            }
        }))
    }
}