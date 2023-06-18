package com.d3if3020.smartmoney.viewmodel

import BankListAdapter
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if3020.smartmoney.model.BankList
import com.d3if3020.smartmoney.network.ApiStatus
import com.d3if3020.smartmoney.network.BankListApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BankListViewModel: ViewModel() {
    val data = MutableLiveData<List<BankList>>()
    val status = MutableLiveData<ApiStatus>()
    private val myAdapter = BankListAdapter()

    init {
        retrieveData()
    }

    fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = BankListApi.service.getBankList()
                Log.d("BankListViewModel", "Success: $result")
                data.postValue(result)
                myAdapter.notifyDataSetChanged() // Tambahkan ini
            } catch (e: Exception) {
                Log.d("BankListViewModel", "Failure: ${e.message}")
            }
        }
    }


    fun getData(): LiveData<List<BankList>> = data
    fun getStatus(): LiveData<ApiStatus> = status
}