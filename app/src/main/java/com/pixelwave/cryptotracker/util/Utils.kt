package com.pixelwave.cryptotracker.util

import java.lang.reflect.Field
import java.text.DecimalFormat

fun getResId(resName: String?, c: Class<*>): Int {
    return try {
        val idField: Field = resName?.let { c.getDeclaredField(it) } ?: return -1
        idField.getInt(idField)
    } catch (e: Exception) {
        e.printStackTrace()
        -1
    }
}

val priceFormat = DecimalFormat("$#,##0.00")
val decimalFormat = DecimalFormat("#0.00")

fun Double.formatPrice(): String {
    return priceFormat.format(this)
}

fun Double.formatChange(): String {
    return decimalFormat.format(this).plus("%")
}