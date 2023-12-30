package com.osvaldo.rrdex.ui.monList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.osvaldo.rrdex.data.Pokemon
import org.koin.androidx.compose.koinViewModel

@Composable
fun PkmnList(vm: PkmnListviewModel = koinViewModel()) {
    vm.intent(
        PkmnListviewModel.PkmnIntent.LoadPokemonMap
    )

    val state = vm.state.collectAsState().value

    PkmnListView(
        isLoading = state.isLoading,
        pkmnList = state.pokemonMap.values.toList()
    )
}

@Composable
fun PkmnListView(isLoading: Boolean, pkmnList: List<Pokemon>) {

    if (isLoading) return

    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(pkmnList) { pkmn ->
            PkmnCard(pkmn = pkmn)
        }
    }
}

@Composable
fun PkmnCard(pkmn: Pokemon) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        pkmn.sprite?.asImageBitmap()?.let {
            Image(
                bitmap = it,
                contentDescription = pkmn.name,
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
            )
        }
        pkmn.family.regionalName?.let { Text(text = it) }
        Text(text = pkmn.name)
        pkmn.family.form?.let { Text(text = it) }
    }
}
