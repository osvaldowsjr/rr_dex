package com.osvaldo.rrdex.useCase

import com.osvaldo.rrdex.core.decodeBitmapList
import com.osvaldo.rrdex.data.dto.pokemon.PokemonDto
import com.osvaldo.rrdex.data.viewObjects.PokemonPageViewObject
import com.osvaldo.rrdex.repository.PokemonRepository
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class FetchPokemonUseCase(
    private val pokemonRepository: PokemonRepository,
    private val coroutineContext: CoroutineContext
) {

    suspend fun fetchPokemon(pokemonId: String) = withContext(coroutineContext) {
        val pokemonList = pokemonRepository.getPokemonList()
        val pokemonSprites = pokemonRepository.getPokemonImageList().decodeBitmapList()
        val pokemon = pokemonList[pokemonId]?.copy(sprite = pokemonSprites[pokemonId])
        return@withContext convertToViewObject(pokemon)
    }

    private suspend fun convertToViewObject(pokemon: PokemonDto?): PokemonPageViewObject? {
        pokemon?.let {
            val types = pokemonRepository.fetchTypes(pokemon.type.primary, pokemon.type.secondary)
            return PokemonPageViewObject(
                sprite = pokemon.sprite,
                name = pokemon.name,
                form = pokemon.family.form,
                regionalName = pokemon.family.regionalName,
                primaryType = types.first,
                secondaryType = types.second,
                key = pokemon.key,
                stats = pokemon.stats,
                abilities = pokemon.abilities
            )
        }
        return null
    }
}