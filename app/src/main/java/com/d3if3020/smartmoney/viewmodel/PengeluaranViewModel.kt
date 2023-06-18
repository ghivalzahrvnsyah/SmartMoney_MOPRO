package com.d3if3020.smartmoney.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if3020.smartmoney.db.DataDao
import com.d3if3020.smartmoney.db.DataEntity
import com.d3if3020.smartmoney.model.BankList
import com.d3if3020.smartmoney.model.Pengeluaran
import com.d3if3020.smartmoney.network.BankListApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PengeluaranViewModel(private val db: DataDao) : ViewModel() {
    private val pengeluaranList = MutableLiveData<Pengeluaran>()

    val data = db.getPengeluaran()


    fun insertPengeluaran(tanggal: String, jumlah_uang: Double, keterangan: String) {
        val data = Pengeluaran(
            tanggal = tanggal,
            jumlah_uang = jumlah_uang,
            keterangan = keterangan,
        )
        pengeluaranList.value = data
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insertPengeluaran(
                    DataEntity(
                        tanggal = tanggal,
                        jumlah_uang = jumlah_uang,
                        keterangan = keterangan,
                        kategori = "Pengeluaran"
                    )
                )
            }
        }
    }

    fun getPengeluaran(): LiveData<Pengeluaran?> = pengeluaranList

    fun hapusSemuaPengeluaran() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.hapusSemuaPengeluaran()
            }
        }
    }

}