package com.example.cryptoapp.service

import com.example.cryptoapp.cryptomodel.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoInterface {

    @GET("/data/v2/histohour?fsym=BTC&tsym=USD&limit=10")
    fun getData(): Call<CryptoModel>
}