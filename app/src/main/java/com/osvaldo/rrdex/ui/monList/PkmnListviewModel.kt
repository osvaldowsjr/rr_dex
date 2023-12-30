package com.osvaldo.rrdex.ui.monList

import androidx.lifecycle.viewModelScope
import com.osvaldo.rrdex.core.BaseMviViewModel
import com.osvaldo.rrdex.data.Pokemon
import com.osvaldo.rrdex.useCase.FetchPokemonListUseCase
import kotlinx.coroutines.launch

class PkmnListviewModel(
    private val fetchPokemonListUseCase: FetchPokemonListUseCase
) : BaseMviViewModel<
        PkmnListviewModel.PkmnIntent, PkmnListviewModel.PkmnListViewState, PkmnListviewModel.PkmnListEffect>() {

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
    }

    sealed class PkmnListEffect : BaseViewEffect

    data class PkmnListViewState(
        val isLoading: Boolean = true,
        val pokemonMap: MutableMap<String, Pokemon> = mutableMapOf()
    ) : BaseViewState

    override fun initialState() = PkmnListViewState()

    override fun intent(intent: PkmnIntent) {
        when (intent) {
            is PkmnIntent.LoadPokemonMap -> loadPokemonMap()
        }
    }
}