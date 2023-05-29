package com.example.ililo.Park.model

import android.util.Log
import com.example.ililo.ApplicationClass.Companion.sRetrofit
import com.example.ililo.BaseResponse
import com.example.ililo.Park.service.ParkRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ParkService(val parkinterface: Parkinterface) {
    private val retrofit: ParkRetrofit = sRetrofit.create(ParkRetrofit::class.java)

    fun tryPostParkRegister(id: Long, exitTime: String, no_departure: Boolean) {
        retrofit.postRegisterTimeReq(id, RegisterReq(exitTime, no_departure)).enqueue((object : Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.isSuccessful) {
                    parkinterface.onPostRegisterSuccess(response.message())
                    Log.d("RegisterTime", "success")
                } else {
                    Log.d("RegisterTime", "failure")
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("tryPostParkRegister", t.message!!)
                t.printStackTrace()
                parkinterface.onPostRegisterFailure(t.message ?: "통신오류")
            }
        }))
    }

    fun tryGetNearVehicle(id: Long) {
        retrofit.getNearVehicleReq(id).enqueue((object : Callback<NearVehicleRes>{
            override fun onResponse(call: Call<NearVehicleRes>, response: Response<NearVehicleRes>) {
                if (response.isSuccessful) {
                    parkinterface.onGetNearVehicleSuccess(response.body() as NearVehicleRes)
                    Log.d("getNearVehicleReq", "success")
                } else {
                    Log.d("getNearVehicleReq", "failure")
                }
            }

            override fun onFailure(call: Call<NearVehicleRes>, t: Throwable) {
                Log.d("tryGetNearVehicle", t.message!!)
                t.printStackTrace()
                parkinterface.onGetNearVehicleFailure(t.message ?: "통신오류")
            }
        }))
    }
}