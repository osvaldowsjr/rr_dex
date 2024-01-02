package com.osvaldo.rrdex.data.dto.pokemon

import android.graphics.Bitmap

data class PokemonDto(
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


