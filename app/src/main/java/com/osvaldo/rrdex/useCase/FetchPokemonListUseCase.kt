package com.osvaldo.rrdex.useCase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.osvaldo.rrdex.data.Pokemon
import com.osvaldo.rrdex.data.RegionInfo
import com.osvaldo.rrdex.repository.PokemonRepository
import kotlinx.coroutines.withContext
import java.io.ByteArrayInputStream
import java.io.InputStream
import kotlin.coroutines.CoroutineContext

class FetchPokemonListUseCase(
    private val pokemonRepository: PokemonRepository,
    private val coroutineContext: CoroutineContext
) {

    suspend fun getPokemonMap() = withContext(coroutineContext) {
        val pokemonMap = pokemonRepository.getPokemonList()
        val bitmapList = decodeBitmapList(pokemonRepository.getPokemonImageList())
        val regionsList = pokemonRepository.getRegionsList()

        pokemonMap.forEach {
            pokemonMap[it.key] =
                addRegionalForm(it.value.copy(sprite = bitmapList[it.key]), regionsList)
        }

        return@withContext pokemonMap
    }

    private fun decodeBitmapList(imageMap: Map<String, String>): Map<String, Bitmap> {
        val newMap = mutableMapOf<String, Bitmap>()
        imageMap.map {
            val base64String = it.value
            val base64EncodedImage = base64String.split(",")[1]
            val decodedBytes: ByteArray = Base64.decode(base64EncodedImage, Base64.DEFAULT)
            val inputStream: InputStream = ByteArrayInputStream(decodedBytes)
            val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
            newMap[it.key] = bitmap
        }
        return newMap
    }

    private fun addRegionalForm(pokemon: Pokemon, regionsList: Map<String, RegionInfo>): Pokemon {
        return if (pokemon.family.variant == null) pokemon
        else {
            pokemon.copy(
                family = pokemon.family.copy(
                    regionalName = regionsList[pokemon.family.region]?.variant,
                )
            )
        }
    }
}
