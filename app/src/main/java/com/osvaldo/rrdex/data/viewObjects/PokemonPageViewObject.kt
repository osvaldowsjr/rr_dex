package com.osvaldo.rrdex.data.viewObjects

import android.graphics.Bitmap
import com.osvaldo.rrdex.data.dto.pokemon.Abilities
import com.osvaldo.rrdex.data.dto.pokemon.Stats

data class PokemonPageViewObject(
    val sprite: Bitmap?,
    val name: String,
    val form: String?,
    val regionalName: String?,
    val primaryType: Pair<String, String>,
    val secondaryType: Pair<String, String>,
    val stats: Stats,
    val key: String,
    val abilities: Abilities
)
