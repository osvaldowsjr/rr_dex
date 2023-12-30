package com.osvaldo.rrdex.data

import android.graphics.Bitmap

data class Pokemon(
    val ID: Int,
    val key: String,
    val dexID: Int,
    val stats: Stats,
    val abilities: Abilities,
    val type: Type,
    val family: Family,
    val learnset: Learnset,
    val cap: Cap,
    val name: String,
    val sprite: Bitmap? = null,
)


