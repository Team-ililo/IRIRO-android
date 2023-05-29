package com.example.ililo.Park.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ililo.ApplicationClass.Companion.prefs
import com.example.ililo.Park.model.NearVehicleList
import com.example.ililo.Park.model.NearVehicleRes
import com.example.ililo.Park.model.ParkService
import com.example.ililo.Park.model.Parkinterface
import com.example.ililo.Park.view.adapter.DepartureListRVAdapter
import com.example.ililo.databinding.ActivityDepartureInfoBinding

class DepartureInfoActivity : AppCompatActivity(), Parkinterface {
    private val binding: ActivityDepartureInfoBinding by lazy {
        ActivityDepartureInfoBinding.inflate(layoutInflater)
    }
    private val device_id = prefs.getLong("device_id",0L)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        ParkService(this).tryGetNearVehicle(device_id)

        binding.btnRefresh.setOnClickListener {
            ParkService(this).tryGetNearVehicle(device_id)
        }

        //뒤로가기
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onPostRegisterSuccess(message: String) { }

    override fun onPostRegisterFailure(message: String) { }

    override fun onGetNearVehicleSuccess(response: NearVehicleRes) {
        val res = response.data

        val nearVehicleList: ArrayList<NearVehicleList> = arrayListOf()
        val listAdapter = DepartureListRVAdapter(nearVehicleList)

        binding.rvDeparture.adapter = listAdapter
        binding.rvDeparture.layoutManager = LinearLayoutManager(this)

        val sortedList = res.sortedBy { !it.isSatisfied }
        listAdapter.updateList(sortedList)
        nearVehicleList.addAll(sortedList)
    }

    override fun onGetNearVehicleFailure(message: String) {
        Log.d("주변차량조회", "failure")
    }
}