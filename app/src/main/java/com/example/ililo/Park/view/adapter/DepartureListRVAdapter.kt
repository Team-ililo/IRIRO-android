package com.example.ililo.Park.view.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ililo.Park.model.NearVehicleList
import com.example.ililo.R
import com.example.ililo.databinding.ItemDepartureListBinding

class DepartureListRVAdapter (private val dataList: ArrayList<NearVehicleList>): RecyclerView.Adapter<DepartureListRVAdapter.DataViewHolder>(){

    inner class DataViewHolder(val binding: ItemDepartureListBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(data: NearVehicleList) {
                binding.tvCarColor.text = data.color
                binding.tvDepartureTime.text = data.exitTime
                binding.tvCar.text = data.model
                binding.tvCarNum.text = data.vehicle_number

                if (data.isLongTermParking == true) {
                    binding.tvDepartureTime.text = "장기주차"
                    binding.tvOut.text = "예정"
                } else if (data.exitTime == null) {
                    binding.tvDepartureTime.text = "등록없음"
                    binding.tvOut.text = "설정"
                    binding.layoutSatisfied.setBackgroundResource(R.drawable.background_grey)  //subColor
                } else if (data.isSatisfied != true) {
                    binding.layoutSatisfied.setBackgroundResource(R.drawable.background_yellow)  //subColor
                }
            }
        }

    // 정렬된 리스트를 저장할 변수 추가
    private var sortedList: List<NearVehicleList> = ArrayList()

    // 리스트 업데이트 메서드 추가
    fun updateList(newList: List<NearVehicleList>) {
        sortedList = newList.sortedBy { !it.isSatisfied }
        notifyDataSetChanged()
    }

    // ViewHolder 만들어질 때 실행할 동작
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemDepartureListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    // ViewHolder가 실제로 데이터를 표시해야 할 때 호출되는 함수
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size
}
