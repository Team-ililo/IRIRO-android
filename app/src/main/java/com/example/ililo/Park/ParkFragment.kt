package com.example.ililo.Park

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import com.example.ililo.Declare.MemberDeclareListActivity
import com.example.ililo.Home.HomeFragment
import com.example.ililo.Notice.NoticeActivity
import com.example.ililo.R
import com.example.ililo.databinding.FragmentParkBinding

class ParkFragment: Fragment() {
    private var _binding: FragmentParkBinding? = null
    private val binding get() = _binding!!
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

        //차량 출차 정보 조회
        binding.layoutDeparture.setOnClickListener{
            val intent = Intent(context, DepartureInfoActivity::class.java)
            startActivity(intent)
        }

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
            if(cBox.isChecked){
                binding.tvRegisterTime.text = "장시간 이동 예정이 없어요!"
                mAlertDialog.dismiss()
            }
            else{
                var hour = (tPicker.hour.toString().padStart(2, '0')).toInt()
                val min = tPicker.minute.toString().padStart(2, '0')
                if (hour > 12) {
                    hour = hour - 12
                    binding.tvRegisterTime.text = "오후 $hour 시 $min 분"
                } else {
                    binding.tvRegisterTime.text = "오전 $hour 시 $min 분"
                }
                mAlertDialog.dismiss()
            }
        }

        cancel.setOnClickListener{
            mAlertDialog.dismiss()
        }
    }
}