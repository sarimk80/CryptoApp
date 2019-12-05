package com.example.cryptoapp.repository

import com.example.cryptoapp.model.Balance
import com.example.cryptoapp.service.BalanceService
import io.reactivex.Single
import javax.inject.Inject

class TransactionRepo @Inject constructor(private val balanceService: BalanceService) {

    fun getTransactionRepo(api_key: String, type: String): Single<Balance> {
        return balanceService.getTransaction(api_key, type)
    }
}