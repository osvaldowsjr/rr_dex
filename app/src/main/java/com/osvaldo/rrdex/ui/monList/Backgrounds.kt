package com.osvaldo.rrdex.ui.monList

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.osvaldo.rrdex.R

fun Modifier.cardBackgroundGradient(): Modifier {
    return this
        .background(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color.White,
                    Color.LightGray
                )
            ),
            shape = RoundedCornerShape(20.dp)
        )
}

fun Modifier.createPokemonImageBackground(): Modifier = composed {
    this.paint(
        painter = painterResource(id = R.drawable.pokeball_icon)
    )
}