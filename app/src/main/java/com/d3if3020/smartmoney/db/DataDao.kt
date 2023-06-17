package com.d3if3020.smartmoney.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataDao{
    // Data Pemasukan
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPemasukan(data: DataEntity)

    @Query("SELECT * FROM tbl_keuangan WHERE kategori = 'Pemasukan'")
    fun getPemasukan(): LiveData<List<DataEntity>>

    @Query("SELECT SUM(jumlah_uang) FROM tbl_keuangan WHERE kategori = 'Pemasukan'")
    fun getTotalPemasukan(): LiveData<Double>

    @Query("DELETE FROM tbl_keuangan WHERE kategori = 'Pemasukan'")
    fun hapusSemuaPemasukan()

    // Data Pengeluaran
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPengeluaran(data: DataEntity)

    @Query("SELECT * FROM tbl_keuangan WHERE kategori = 'Pengeluaran'")
    fun getPengeluaran(): LiveData<List<DataEntity>>

    @Query("SELECT SUM(jumlah_uang) FROM tbl_keuangan WHERE kategori = 'Pengeluaran'")
    fun getTotalPengeluaran(): LiveData<Double>

    @Query("DELETE FROM tbl_keuangan WHERE kategori = 'Pengeluaran'")
    fun hapusSemuaPengeluaran()


}