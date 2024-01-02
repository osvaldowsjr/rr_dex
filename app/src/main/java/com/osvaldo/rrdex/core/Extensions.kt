package com.osvaldo.rrdex.core

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.ui.graphics.Color
import java.io.ByteArrayInputStream
import java.io.InputStream

fun Color.Companion.fromHex(colorString: String) =
    Color(android.graphics.Color.parseColor(colorString))

fun Map<String, String>.decodeBitmapList(): Map<String, Bitmap> {
    val newMap = mutableMapOf<String, Bitmap>()
    this.map {
        val base64String = it.value
        val base64EncodedImage = base64String.split(",")[1]
        val decodedBytes: ByteArray = Base64.decode(base64EncodedImage, Base64.DEFAULT)
        val inputStream: InputStream = ByteArrayInputStream(decodedBytes)
        val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
        newMap[it.key] = bitmap
    }
    return newMap
}