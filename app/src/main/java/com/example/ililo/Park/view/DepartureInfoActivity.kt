package com.example.ililo.Park.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ililo.ApplicationClass.Companion.prefs
import com.example.ililo.Park.model.NearVehicleList
import com.example.ililo.Park.model.NearVehicleRes
import com.example.ililo.Park.service.ParkService
import com.example.ililo.Park.model.Parkinterface
import com.example.ililo.Park.view.adapter.DepartureListRVAdapter
import com.example.ililo.databinding.ActivityDepartureInfoBinding

class DepartureInfoActivity : AppCompatActivity(), Parkinterface {
    private val binding: ActivityDepartureInfoBinding by lazy {
        ActivityDepartureInfoBinding.inflate(layoutInflater)
    }
    private val device_id = prefs.getString("device_id","")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (device_id != null) {
            ParkService(this).tryGetNearVehicle(device_id)
        }

        //새로고침 버튼 그림자 주기
        ViewCompat.setElevation(binding.btnRefresh, 10f)

        //새로고침
        binding.btnRefresh.setOnClickListener {
            if (device_id != null) {
                ParkService(this).tryGetNearVehicle(device_id)
            }
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
        binding.tvDepartureError.text = message
    }
}