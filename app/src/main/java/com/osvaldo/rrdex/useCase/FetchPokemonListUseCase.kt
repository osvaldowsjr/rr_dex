package com.osvaldo.rrdex.useCase

import com.osvaldo.rrdex.core.decodeBitmapList
import com.osvaldo.rrdex.data.dto.RegionInfo
import com.osvaldo.rrdex.data.dto.pokemon.PokemonDto
import com.osvaldo.rrdex.data.viewObjects.PokemonListViewObject
import com.osvaldo.rrdex.repository.PokemonRepository
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class FetchPokemonListUseCase(
    private val pokemonRepository: PokemonRepository,
    private val coroutineContext: CoroutineContext
) {

    suspend fun getPokemonMap() = withContext(coroutineContext) {
        val pokemonMap = pokemonRepository.getPokemonList()
        val bitmapList = pokemonRepository.getPokemonImageList().decodeBitmapList()
        val regionsList = pokemonRepository.getRegionsList()
        val pokemonListVOMap: MutableMap<String, PokemonListViewObject> = mutableMapOf()
        pokemonMap.forEach {
            val pokemonListViewObject =
                convertToViewObject(
                    addRegionalForm(
                        it.value.copy(sprite = bitmapList[it.key]),
                        regionsList
                    )
                )

            pokemonListVOMap[it.key] = pokemonListViewObject
        }

        return@withContext pokemonListVOMap
    }

    private suspend fun convertToViewObject(pokemon: PokemonDto): PokemonListViewObject {
        val types = pokemonRepository.fetchTypes(pokemon.type.primary, pokemon.type.secondary)
        return PokemonListViewObject(
            sprite = pokemon.sprite,
            name = pokemon.name,
            form = pokemon.family.form,
            regionalName = pokemon.family.regionalName,
            primaryType = types.first,
            secondaryType = types.second,
            key = pokemon.key
        )
    }

    private fun addRegionalForm(
        pokemon: PokemonDto,
        regionsList: Map<String, RegionInfo>
    ): PokemonDto {
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
