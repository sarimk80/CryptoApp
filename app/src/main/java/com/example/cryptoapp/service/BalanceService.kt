package com.example.cryptoapp.service

import com.example.cryptoapp.model.Balance
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface BalanceService {

    @GET("get_balance/")
    fun getBalance(@Query("api_key") api_key: String): Single<Balance>
}