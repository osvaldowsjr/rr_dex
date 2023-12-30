package com.osvaldo.rrdex.data

data class Family(
    val eggGroup: EggGroup,
    val evolutions: List<List<Any>>?,
    val ancestor: String,
    val region: String,
    val forms: List<String>? = null,
    val form: String? = null,
    val variant: Int? = null,
    val regionalName: String? = null,
)
