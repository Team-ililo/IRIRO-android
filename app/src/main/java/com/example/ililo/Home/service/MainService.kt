package com.example.ililo.Home.service

import android.util.Log
import com.example.ililo.ApplicationClass.Companion.sRetrofit
import com.example.ililo.Home.model.MainRes
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainService(val mainInterface: MainInterface) {
    private val retrofit: MainRetrofit = sRetrofit.create(MainRetrofit::class.java)

    fun tryGetMain(id: Long) {
        retrofit.getMainRes(id).enqueue(object : Callback<MainRes>{
            override fun onResponse(call: Call<MainRes>, response: Response<MainRes>) {
                if (response.isSuccessful) {
                    mainInterface.onGetMainSuccess(response.body() as MainRes)
                    Log.d("tryGetMain", "success")
                } else {
                    val errorResponse = response.errorBody()?.string()
                    val errorMessage = try {
                        val errorJson = JSONObject(errorResponse)
                        errorJson.getString("message")
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        null
                    }
                    mainInterface.onGetMainFailure(errorMessage ?: "통신오류")
                }
            }

            override fun onFailure(call: Call<MainRes>, t: Throwable) {
                Log.d("tryPostDeclare", t.message!!)
                t.printStackTrace()
                mainInterface.onGetMainFailure(t.message ?: "통신오류")
            }

        })
    }
}