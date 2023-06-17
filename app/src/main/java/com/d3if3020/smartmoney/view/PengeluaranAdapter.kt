package com.d3if3020.smartmoney.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.d3if3020.smartmoney.databinding.ListItemDataBinding
import com.d3if3020.smartmoney.db.DataEntity
import com.d3if3020.smartmoney.utils.FunctionHelper

class PengeluaranAdapter: ListAdapter<DataEntity, PengeluaranAdapter.ViewHolder>(DIFF_CALLBACK) {
    private lateinit var myAdapter: PengeluaranAdapter
    private lateinit var addDataFragment: AddPemasukanFragment



    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataEntity>(){
            override fun areItemsTheSame(oldItem: DataEntity, newItem: DataEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DataEntity, newItem: DataEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
    inner class ViewHolder(private val binding: ListItemDataBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataEntity) = with(binding) {
            val price = data.jumlah_uang
            val formattedPrice = FunctionHelper.formatPrice(price)

            binding.tvDate.text = data.tanggal
            binding.tvPrice.text = formattedPrice
            binding.tvNote.text = data.keterangan

            binding.btnShare.setOnClickListener {
                shareData()
            }
        }
        fun shareData() {
            val tanggal = binding.tvDate.text.toString()
            val jumlah_uang = binding.tvPrice.text.toString()
            val keterangan = binding.tvNote.text.toString()
            val sendIntent: String = "Tanggal: $tanggal \nJumlah Uang: $jumlah_uang \nKeterangan: $keterangan"
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_TEXT, sendIntent)
            shareIntent.type = "text/plain"
            ContextCompat.startActivity(binding.root.context, shareIntent, null)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemDataBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)

    }


    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    fun getTotalPengeluaran(): Double {
        var total = 0.0
        for (data in currentList) {
            total += data.jumlah_uang
        }
        return total
    }

}