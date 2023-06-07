package com.d3if3020.smartmoney.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_keuangan")
data class DataEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0L,
    val tanggal: String,
    val kategori: String,
    val jumlah_uang: Double,
    val keterangan: String
)
