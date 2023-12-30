package com.osvaldo.rrdex.data

data class Learnset(
    val levelup: List<List<Any>>,
    val egg: List<String>,
    val TM: List<String>,
    val tutor: List<String>,
    val prevo: List<List<Any>>? = null
)