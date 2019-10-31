package com.example.cryptoapp.repository

import com.example.cryptoapp.model.Balance
import com.example.cryptoapp.service.BalanceService
import io.reactivex.Single
import javax.inject.Inject

class BalanceRepo @Inject constructor(private val balanceService: BalanceService) {

    fun getBalance_Repo(apiKey: String): Single<Balance> {
        return balanceService.getBalance(apiKey)
    }
}