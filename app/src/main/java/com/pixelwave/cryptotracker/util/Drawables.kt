package com.pixelwave.cryptotracker.util

import java.lang.reflect.Field

fun getResId(resName: String?, c: Class<*>): Int {
    return try {
        val idField: Field = resName?.let { c.getDeclaredField(it) } ?: return -1
        idField.getInt(idField)
    } catch (e: Exception) {
        e.printStackTrace()
        -1
    }
}