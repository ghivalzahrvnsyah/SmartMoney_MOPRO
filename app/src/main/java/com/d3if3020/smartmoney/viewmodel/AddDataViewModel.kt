package com.d3if3020.smartmoney.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddDataViewModel: ViewModel() {

    private val _incomeList = MutableLiveData<List<Double>>()
    val incomeList: LiveData<List<Double>>
        get() = _incomeList

    private val _expenseList = MutableLiveData<List<Double>>()
    val expenseList: LiveData<List<Double>>
        get() = _expenseList

    fun addIncome(amount: Double) {
        val currentList = _incomeList.value.orEmpty().toMutableList()
        currentList.add(amount)
        _incomeList.value = currentList
    }

    fun addExpense(amount: Double) {
        val currentList = _expenseList.value.orEmpty().toMutableList()
        currentList.add(amount)
        _expenseList.value = currentList
    }
}