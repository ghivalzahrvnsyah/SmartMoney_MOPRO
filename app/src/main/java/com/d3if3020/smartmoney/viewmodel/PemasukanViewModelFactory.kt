package com.d3if3020.smartmoney.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if3020.smartmoney.db.DataDao

class PemasukanViewModelFactory(
    private val db: DataDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PemasukanViewModel::class.java)) {
            return PemasukanViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}