package com.d3if3020.smartmoney.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if3020.smartmoney.db.DataDao
import com.d3if3020.smartmoney.db.DataEntity
import com.d3if3020.smartmoney.model.BankList
import com.d3if3020.smartmoney.model.Pemasukan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PemasukanViewModel(private val db: DataDao) : ViewModel() {

    private val pemasukanList = MutableLiveData<Pemasukan>()
    private val totalPemasukan = MutableLiveData<Double>()
    private val list = MutableLiveData<List<BankList>>()

    val data = db.getPemasukan()

//    init {
//        retrieveData()
//    }
//
//
//    private fun retrieveData() {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val result = BankListApi.service.getBankList()
//                list.postValue(BankListApi.service.getBankList())
//                Log.d("BankListViewModel", "Result: $result")
//
//            } catch (e: Exception) {
//                Log.e("BankListViewModel", "Error retrieving bank list: ${e.message}")
//            }
//        }
//    }
    fun getList(): LiveData<List<BankList>> = list

    fun insertPemasukan(tanggal: String, jumlah_uang: Double, keterangan: String) {
        val data = Pemasukan(
            tanggal = tanggal,
            jumlah_uang = jumlah_uang,
            keterangan = keterangan,
        )
        pemasukanList.value = data
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insertPemasukan(
                    DataEntity(
                        tanggal = tanggal,
                        jumlah_uang = jumlah_uang,
                        keterangan = keterangan,
                        kategori = "Pemasukan"
                    )
                )
            }
        }
    }
    fun getPemasukan(): LiveData<Pemasukan?> = pemasukanList

    fun hitungTotalPemasukan() {
        viewModelScope.launch {
            val total = withContext(Dispatchers.IO) {
                db.getTotalPemasukan()
            }
            totalPemasukan.value = data.value?.sumByDouble { it.jumlah_uang }
        }
    }
    fun getTotalPemasukan(): LiveData<Double?> = totalPemasukan

    fun hapusSemuaPemasukan() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.hapusSemuaPemasukan()
            }
        }
    }

}

