package com.osvaldo.rrdex.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.osvaldo.rrdex.core.JsonLoader
import com.osvaldo.rrdex.data.dto.RegionInfo
import com.osvaldo.rrdex.data.dto.pokemon.PokemonDto
import com.osvaldo.rrdex.data.dto.typing.Typing

class PokemonRepository(
    private val jsonLoader: JsonLoader
) {

    fun getPokemonList(): MutableMap<String, PokemonDto> {
        val gson = Gson()
        return gson.fromJson(
            jsonLoader.loadJson(fileName = "mons.json"),
            object : TypeToken<Map<String, PokemonDto>>() {}.type
        )
    }

    fun getPokemonImageList(): Map<String, String> {
        val gson = Gson()
        return gson.fromJson(
            jsonLoader.loadJson(
                fileName = "sprites.json"
            ), object : TypeToken<Map<String, String>>() {}.type
        )
    }

    fun getRegionsList(): Map<String, RegionInfo> {
        val gson = Gson()
        return gson.fromJson(
            jsonLoader.loadJson(
                fileName = "regions.json"
            ), object : TypeToken<Map<String, RegionInfo>>() {}.type
        )
    }

    private fun getTypesList(): Map<String, Typing> {
        val gson = Gson()
        return gson.fromJson(
            jsonLoader.loadJson(
                fileName = "types.json"
            ), object : TypeToken<Map<String, Typing>>() {}.type
        )
    }

    suspend fun fetchTypes(
        primaryType: String,
        secondaryType: String?
    ): Pair<Pair<String, String>, Pair<String, String>> {
        val typingList = getTypesList()

        val primaryPair =
            Pair(typingList[primaryType]?.name ?: "", typingList[primaryType]?.color ?: "")

        val secondaryPair =
            Pair(typingList[secondaryType]?.name ?: "", typingList[secondaryType]?.color ?: "")


        return Pair(primaryPair, secondaryPair)
    }

}