package com.d3if3020.smartmoney.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.d3if3020.smartmoney.databinding.ListItemBankBinding
import com.d3if3020.smartmoney.model.BankList

class BankListAdapter : ListAdapter<BankList, BankListAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBankBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ListItemBankBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BankList) {
            with(binding) {
                tvBankName.text = data.bankName
                tvBankTelp.text = data.bankTelp
                tvBankWebsite.text = data.bankWebsite

            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BankList>() {
            override fun areItemsTheSame(oldItem: BankList, newItem: BankList): Boolean {
                return oldItem.bankName == newItem.bankName
            }

            override fun areContentsTheSame(oldItem: BankList, newItem: BankList): Boolean {
                return oldItem == newItem
            }
        }
    }
}
