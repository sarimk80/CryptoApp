package com.example.cryptoapp.repository

import com.example.cryptoapp.model.Balance
import com.example.cryptoapp.service.BalanceService
import io.reactivex.Single
import javax.inject.Inject

class WithDrawCoinRepo @Inject constructor(private val balanceService: BalanceService) {


    fun getWithDrawCoinRepo(api_key: String, amount: String, to_address: String): Single<Balance> {

        return balanceService.withDrawCoin(api_key, amount, to_address)

    }
}