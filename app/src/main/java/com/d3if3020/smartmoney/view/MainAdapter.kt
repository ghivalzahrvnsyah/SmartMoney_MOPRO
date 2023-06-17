package com.d3if3020.smartmoney.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.d3if3020.smartmoney.databinding.ListItemDataBinding
import com.d3if3020.smartmoney.model.Pemasukan

class MainAdapter(private val list:ArrayList<Pemasukan>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = mutableListOf<Pemasukan>()
    private lateinit var binding: ListItemDataBinding

    class ViewHolder(private val binding: ListItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Pemasukan) = with(binding) {
            tvDate.text = data.tanggal
            tvPrice.text = data.jumlah_uang.toString()
            tvNote.text = data.keterangan
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ListItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val list = list[position]
        if (holder is ViewHolder) {
            holder.bind(list)
        }
    }

    override fun getItemCount(): Int {

        return list.size
    }
}
