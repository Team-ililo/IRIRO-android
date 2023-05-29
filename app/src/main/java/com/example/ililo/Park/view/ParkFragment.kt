package com.example.ililo.Park.view

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import com.example.ililo.ApplicationClass
import com.example.ililo.Home.model.MainRes
import com.example.ililo.Home.service.MainInterface
import com.example.ililo.Home.service.MainService
import com.example.ililo.Park.model.NearVehicleRes
import com.example.ililo.Park.model.ParkService
import com.example.ililo.Park.model.Parkinterface
import com.example.ililo.R
import com.example.ililo.databinding.FragmentParkBinding

class ParkFragment: Fragment(), MainInterface, Parkinterface {
    private var _binding: FragmentParkBinding? = null
    private val binding get() = _binding!!
    private val vehicle_id = ApplicationClass.prefs.getLong("vehicle_id",0L)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentParkBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MainService(this).tryGetMain(vehicle_id)

        //차량 출차 정보 조회
        binding.layoutDeparture.setOnClickListener{
            val intent = Intent(context, DepartureInfoActivity::class.java)
            startActivity(intent)
        }

        //출차 시간 등록
        binding.layoutRegister.setOnClickListener{
            registerTime()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun registerTime(){

        val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_timepicker, null)
        val mBuilder = android.app.AlertDialog.Builder(context).setView(mDialogView)
        val mAlertDialog = mBuilder.show()

        val tPicker = mDialogView.findViewById<TimePicker>(R.id.timePicker)
        val cBox = mDialogView.findViewById<CheckBox>(R.id.checkBox)
        val save = mDialogView.findViewById<TextView>(R.id.btn_save)
        val cancel = mDialogView.findViewById<TextView>(R.id.btn_cancel)

        mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))  //배경 투명처리 해줘야 배경 모양 드러남

        save.setOnClickListener{
            var hour = ""
            var min = ""
            var exitTime = ""
            var departure = false

            if(cBox.isChecked){
                binding.tvRegisterTime.text = "장시간 이동 예정이 없어요!"
                departure = true
                exitTime = ""
                mAlertDialog.dismiss()
            }
            else{
                hour = (tPicker.hour.toString().padStart(2, '0'))
                min = tPicker.minute.toString().padStart(2, '0')
                exitTime = hour + ":" + min
                mAlertDialog.dismiss()
            }

            ParkService(this).tryPostParkRegister(vehicle_id, exitTime, departure)

        }

        cancel.setOnClickListener{
            mAlertDialog.dismiss()
        }
    }

    override fun onGetMainSuccess(response: MainRes) {
        val res = response.data

        if (res.exitTime != null && res.isLongTermParking != null) {
            var hour = res.exitTime.substring(0,2).toInt()
            val min = res.exitTime.substring(3,5).toInt()

            if(hour > 12){
                //24시간 기준 오후
                hour = hour - 12
                binding.tvRegisterTime.text = "오후 " + hour.toString() + "시 " + min.toString() +"분"
            } else {
                //24시간 기준 오전
                binding.tvRegisterTime.text = "오전 " + hour.toString() + "시 " + min.toString() +"분"
            }
        } else if (res.isLongTermParking != null) {
            binding.tvRegisterTime.text = "장시간 이동 예정이 없어요!"
        }

    }

    override fun onGetMainFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onPostRegisterSuccess(message: String) {
        Log.d("등록하기","성공")
        //새로고침 역할  --> 새로고침이 안됌..
        MainService(this).tryGetMain(vehicle_id)
    }

    override fun onPostRegisterFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetNearVehicleSuccess(response: NearVehicleRes) {
        TODO("Not yet implemented")
    }

    override fun onGetNearVehicleFailure(message: String) {
        TODO("Not yet implemented")
    }
}