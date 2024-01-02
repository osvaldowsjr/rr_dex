package com.osvaldo.rrdex.ui.monList.typing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.osvaldo.rrdex.core.fromHex

@Composable
fun PkmnTypingContent(
    primaryTyping: Pair<String, String>,
    secondaryTyping: Pair<String, String>
) {
    Row(
        modifier = Modifier.padding(top = 10.dp)
    ) {
        TypingItem(primaryTyping)
        if (secondaryTyping.first.isNotEmpty())
            TypingItem(secondaryTyping)
    }
}

@Composable
private fun TypingItem(typing: Pair<String, String>) {
    Row(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .background(
                color = Color.fromHex(typing.second),
                shape = RoundedCornerShape(100.dp)
            )
    ) {
        Text(
            text = typing.first,
            fontFamily = FontFamily.SansSerif,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
        )
    }
}

@Preview
@Composable
fun PkmnTypingPreview() {
    PkmnTypingContent(
        primaryTyping = Pair("Grass", "#EE8130"),
        secondaryTyping = Pair("Poison", "#F95587")
    )
}