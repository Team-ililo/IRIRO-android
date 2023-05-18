package com.example.ililo.Notice.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ililo.Notice.model.NoticeList
import com.example.ililo.databinding.ItemDeclareNoticeListBinding

class NoticeListRVAdapter (private val dataList: ArrayList<NoticeList>): RecyclerView.Adapter<NoticeListRVAdapter.DataViewHolder>(){

    inner class DataViewHolder(val binding: ItemDeclareNoticeListBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(data: NoticeList) {
                binding.tvDeclare.text = data.notice
                binding.tvDeclareDate.text = data.apartment_notice_date
            }
        }

    // ViewHolder 만들어질 때 실행할 동작
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemDeclareNoticeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    // ViewHolder가 실제로 데이터를 표시해야 할 때 호출되는 함수
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

}