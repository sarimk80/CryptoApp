package com.example.cryptoapp.cryptomodel

data class Data(
    val Aggregated: Boolean,
    val Data: List<DataX>,
    val TimeFrom: Int,
    val TimeTo: Int
)