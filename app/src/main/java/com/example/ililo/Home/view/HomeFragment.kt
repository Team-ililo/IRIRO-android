package com.example.ililo.Home.view

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ililo.ApplicationClass.Companion.prefs
import com.example.ililo.Declare.view.MemberDeclareListActivity
import com.example.ililo.Home.model.MainRes
import com.example.ililo.Home.service.MainInterface
import com.example.ililo.Home.service.MainService
import com.example.ililo.Notice.model.NoticeList
import com.example.ililo.Notice.model.NoticeRes
import com.example.ililo.Notice.service.NoticeListInterface
import com.example.ililo.Notice.service.NoticeService
import com.example.ililo.Notice.view.NoticeActivity
import com.example.ililo.Notice.view.adapter.NoticeListRVAdapter
import com.example.ililo.Park.model.NearVehicleRes
import com.example.ililo.Park.model.ParkService
import com.example.ililo.Park.model.Parkinterface
import com.example.ililo.R
import com.example.ililo.databinding.FragmentHomeBinding


class HomeFragment: Fragment(), NoticeListInterface, MainInterface, Parkinterface {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val name = prefs.getString("apartment_name","푸르지오")
    private val vehicle_id = prefs.getLong("vehicle_id",0L)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (name != null) {
            NoticeService(this).tryGetNoticeList(name)
        }

        MainService(this).tryGetMain(vehicle_id)

        //공지사항 이동
        binding.tvNotice.setOnClickListener {
            val intent = Intent(context, NoticeActivity::class.java)
            startActivity(intent)
        }

        binding.btnGoNotice.setOnClickListener {
            val intent = Intent(context, NoticeActivity::class.java)
            startActivity(intent)
        }

        binding.layoutNotice.setOnClickListener {
            val intent = Intent(context, NoticeActivity::class.java)
            startActivity(intent)
        }

        //신고내역 이동
        binding.btnDeclareMember.setOnClickListener {
            val intent = Intent(context, MemberDeclareListActivity::class.java)
            startActivity(intent)
        }

        //출차시간 등록
        binding.layoutRegister.setOnClickListener {
            registerTime()
        }
    }

    // Fragment 새로고침
//    fun refreshFragment(fragment: Fragment, fragmentManager: FragmentManager) {
//        var ft: FragmentTransaction = fragmentManager.beginTransaction()
//        ft.detach(fragment).attach(fragment).commit()
//    }

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
                binding.tvBoxMessage.text = "장시간 이동 예정이 없어요!"
                binding.tvGoOutTime.text = "장기 주차 예정"
                binding.tvTime.text = "-"
                departure = true
                exitTime = ""
                mAlertDialog.dismiss()
            }
            else{
                binding.tvBoxMessage.text = "남았어요!"
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

    override fun onGetNoticeListSuccess(response: NoticeRes) {
        Log.d("공지사항", "success")

        val res = response.data

        val noticeList: ArrayList<NoticeList> = arrayListOf()
        val listAdapter = NoticeListRVAdapter(noticeList, 0)

        binding.rvHomeNotice.adapter = listAdapter
        binding.rvHomeNotice.layoutManager = LinearLayoutManager(context)

        noticeList.addAll(res)
    }

    override fun onGetNoticeListFailure(message: String) {
        binding.tvError.text = message
    }

    override fun onGetMainSuccess(response: MainRes) {
        val res = response.data

        binding.tvAddress.text = res.address + "님"
        binding.tvApartment.text = res.apartmentName
        binding.tvAddressDown.text = res.address + "님"

        if (res.exitTime != null && res.isLongTermParking != null) {
            var hour = res.exitTime.substring(0,2).toInt()
            val min = res.exitTime.substring(3,5).toInt()

            binding.tvBoxMessage.text = "남았어요!"

            binding.tvTime.text = res.remainingTime.substring(0,2) + "시간 " + res.remainingTime.substring(3,5) +"분"

            if(hour > 12){
                //24시간 기준 오후
                hour = hour - 12
                binding.tvGoOutTime.text = "오후 " + hour.toString() + "시 " + min.toString() +"분"
                binding.tvRegisterTime.text = "오후 " + hour.toString() + "시 " + min.toString() +"분"
            } else {
                //24시간 기준 오전
                binding.tvGoOutTime.text = "오전 " + hour.toString() + "시 " + min.toString() +"분"
                binding.tvRegisterTime.text = "오전 " + hour.toString() + "시 " + min.toString() +"분"
            }
        } else if (res.exitTime == null && res.isLongTermParking == true) {
            binding.tvRegisterTime.text = "장시간 이동 예정이 없어요!"
            binding.tvBoxMessage.text = "장시간 이동 예정이 없어요!"
            binding.tvGoOutTime.text = "장기 주차 예정"
        }

//        새로고침
//        val ft: FragmentTransaction = requireFragmentManager().beginTransaction()
//        ft.detach(this).attach(this).commit()

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