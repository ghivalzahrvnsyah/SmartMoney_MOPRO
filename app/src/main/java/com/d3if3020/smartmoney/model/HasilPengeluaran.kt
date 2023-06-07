package com.d3if3020.smartmoney.model

fun Pengeluaran.HasilTransaksi(): Pengeluaran {
    val tanggal = tanggal
    val jumlah_uang = jumlah_uang
    val keterangan = keterangan
    return Pengeluaran(tanggal, jumlah_uang, keterangan)
}