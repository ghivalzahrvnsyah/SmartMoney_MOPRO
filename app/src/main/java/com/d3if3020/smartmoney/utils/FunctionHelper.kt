package com.d3if3020.smartmoney.utils

import java.util.*

object FunctionHelper {
    fun formatPrice(price: Double): String {
        return "Rp${price.toInt()},00"
    }
    fun formatDate(calendar: Calendar): String {
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1
        val year = calendar.get(Calendar.YEAR)

        return String.format(Locale.getDefault(), "%02d/%02d/%d", day, month, year)
    }
}