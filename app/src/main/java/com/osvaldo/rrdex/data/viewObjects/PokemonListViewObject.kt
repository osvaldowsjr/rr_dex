package com.osvaldo.rrdex.data.viewObjects

import android.graphics.Bitmap

data class PokemonListViewObject(
    val sprite: Bitmap?,
    val name: String,
    val form: String?,
    val regionalName: String?,
    val primaryType: Pair<String,String>,
    val secondaryType: Pair<String,String>,
    val key: String
)