package com.osvaldo.rrdex.ui.monList

import androidx.lifecycle.viewModelScope
import com.osvaldo.rrdex.core.BaseMviViewModel
import com.osvaldo.rrdex.data.viewObjects.PokemonListViewObject
import com.osvaldo.rrdex.useCase.FetchPokemonListUseCase
import kotlinx.coroutines.launch

class PkmnListViewModel(
    private val fetchPokemonListUseCase: FetchPokemonListUseCase
) : BaseMviViewModel<
        PkmnListViewModel.PkmnIntent, PkmnListViewModel.PkmnListViewState, PkmnListViewModel.PkmnListEffect>() {

    private fun loadPokemonMap(
    ) = viewModelScope.launch {
        val pokemonMap = fetchPokemonListUseCase.getPokemonMap()
        setState {
            copy(
                pokemonMap = pokemonMap,
                isLoading = false
            )
        }
    }

    sealed class PkmnIntent : BaseViewIntent {
        data object LoadPokemonMap : PkmnIntent()
        data class SelectPokemon(val pokemonKey: String) : PkmnIntent()
    }

    sealed class PkmnListEffect : BaseViewEffect {
        data class NavigateToPokemonPage(val pokemonKey: String) : PkmnListEffect()
    }

    data class PkmnListViewState(
        val isLoading: Boolean = true,
        val pokemonMap: MutableMap<String, PokemonListViewObject> = mutableMapOf()
    ) : BaseViewState

    override fun initialState() = PkmnListViewState()

    override fun intent(intent: PkmnIntent) {
        when (intent) {
            is PkmnIntent.LoadPokemonMap -> loadPokemonMap()
            is PkmnIntent.SelectPokemon -> {
                setEffect {
                    PkmnListEffect.NavigateToPokemonPage(intent.pokemonKey)
                }
            }
        }
    }
}