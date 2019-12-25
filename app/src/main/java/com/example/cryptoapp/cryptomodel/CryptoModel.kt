package com.example.cryptoapp.cryptomodel

data class CryptoModel(
    val Data: Data,
    val HasWarning: Boolean,
    val Message: String,
    val RateLimit: RateLimit,
    val Response: String,
    val Type: Int
)