package com.d3if3020.smartmoney.model

fun Pemasukan.HasilTransaksi(): Pemasukan {
    val tanggal = tanggal
    val jumlah_uang = jumlah_uang
    val keterangan = keterangan
    return Pemasukan(tanggal, jumlah_uang, keterangan)
}