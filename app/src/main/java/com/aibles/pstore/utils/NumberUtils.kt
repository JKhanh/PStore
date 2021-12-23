package com.aibles.pstore.utils

import java.text.NumberFormat
import java.util.*

fun Double.toTextPrice(): String{
    val formatter = NumberFormat.getCurrencyInstance()
    formatter.apply {
        maximumFractionDigits = 0
        currency = Currency.getInstance("VND")
    }
    return formatter.format(this)
}