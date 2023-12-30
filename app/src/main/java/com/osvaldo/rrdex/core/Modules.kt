package com.osvaldo.rrdex.core

import com.osvaldo.rrdex.repository.PokemonRepository
import com.osvaldo.rrdex.ui.monList.PkmnListviewModel
import com.osvaldo.rrdex.useCase.FetchPokemonListUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myAppModules = module {
    viewModel { PkmnListviewModel(get()) }

    single { PokemonRepository(get()) }

    single { FetchPokemonListUseCase(get(), Dispatchers.IO) }

    single { JsonLoader(androidContext()) }
}