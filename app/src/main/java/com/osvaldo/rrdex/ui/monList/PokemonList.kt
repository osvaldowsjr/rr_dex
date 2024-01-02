package com.osvaldo.rrdex.ui.monList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.osvaldo.rrdex.data.viewObjects.PokemonListViewObject
import com.osvaldo.rrdex.ui.monList.typing.PkmnTypingContent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonList(
    navController: NavController,
    vm: PkmnListViewModel = koinViewModel()
) {
    vm.intent(
        PkmnListViewModel.PkmnIntent.LoadPokemonMap
    )

    val state = vm.state.collectAsState().value

    val effect = vm.viewEffect
    LaunchedEffect("KEY") {
        effect.onEach {
            when (it) {
                is PkmnListViewModel.PkmnListEffect.NavigateToPokemonPage -> {
                    navController.navigate("pokemonPage/${it.pokemonKey}")
                }
            }
        }.collect()
    }

    PkmnListView(
        isLoading = state.isLoading,
        pkmnList = state.pokemonMap.values.toList()
    ) {
        vm.intent(
            PkmnListViewModel.PkmnIntent.SelectPokemon(it)
        )
    }
}

@Composable
fun PkmnListView(
    isLoading: Boolean,
    pkmnList: List<PokemonListViewObject>,
    navigateAction: (String) -> Unit
) {
    if (isLoading) return
    Column(modifier = Modifier.background(Color.LightGray)) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(pkmnList) { pkmn ->
                PkmnCard(pkmn = pkmn, navigateAction)
            }
        }
    }
}

@Composable
fun PkmnCard(
    pkmn: PokemonListViewObject,
    navigateAction: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .height(300.dp)
            .cardBackgroundGradient()
            .clickable {
                navigateAction(pkmn.key)
            }
            .border(1.dp, Color.Black, RoundedCornerShape(20.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PokemonImage(pkmn)
        PokemonRegionalName(pkmn)
        Text(text = pkmn.name)
        PokemonForm(pkmn)
        PokemonTypes(pkmn)
    }
}

@Composable
fun PokemonTypes(
    pkmn: PokemonListViewObject
) {
    PkmnTypingContent(
        pkmn.primaryType, pkmn.secondaryType
    )
}

@Composable
private fun PokemonForm(pkmn: PokemonListViewObject) {
    pkmn.form?.let { Text(text = it) }
}

@Composable
private fun PokemonRegionalName(pkmn: PokemonListViewObject) {
    pkmn.regionalName?.let { Text(text = it) }
}

@Composable
private fun PokemonImage(pkmn: PokemonListViewObject) {
    pkmn.sprite?.asImageBitmap()?.let {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .createPokemonImageBackground()
        ) {
            Image(
                bitmap = it,
                contentDescription = pkmn.name,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )
        }
    }
}
