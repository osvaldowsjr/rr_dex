package com.osvaldo.rrdex.core

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

class JsonLoader(
    private val context: Context
) {
    fun loadJson(fileName: String): String {
        return try {
            val file = context.assets.open(fileName)
            val bufferedReader = BufferedReader(InputStreamReader(file))
            val stringBuilder = StringBuilder()
            bufferedReader.useLines { lines ->
                lines.forEach {
                    stringBuilder.append(it)
                }
            }
            val jsonString = stringBuilder.toString()
            jsonString
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}