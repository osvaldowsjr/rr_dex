package com.osvaldo.rrdex.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.osvaldo.rrdex.core.JsonLoader
import com.osvaldo.rrdex.data.Pokemon
import com.osvaldo.rrdex.data.RegionInfo

class PokemonRepository(
    private val jsonLoader: JsonLoader
) {

    fun getPokemonList(): MutableMap<String, Pokemon> {
        val gson = Gson()
        return gson.fromJson(
            jsonLoader.loadJson(fileName = "mons.json"),
            object : TypeToken<Map<String, Pokemon>>() {}.type
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

    fun getRegionsList(): Map<String, RegionInfo>{
        val gson = Gson()
        return gson.fromJson(
            jsonLoader.loadJson(
                fileName = "regions.json"
            ), object : TypeToken<Map<String, RegionInfo>>() {}.type
        )
    }

}