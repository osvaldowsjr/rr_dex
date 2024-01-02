package com.osvaldo.rrdex.data.dto.typing

data class Typing(
    val ID: Int,
    val key: String,
    val defensive: Map<String, Double>,
    val name: String,
    val color: String
)
