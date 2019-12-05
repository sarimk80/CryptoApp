package com.example.cryptoapp.service

import com.example.cryptoapp.model.Balance
import io.reactivex.Single
import retrofit2.http.*

interface BalanceService {

    @GET("get_balance/")
    fun getBalance(@Query("api_key") api_key: String): Single<Balance>

    @POST("withdraw/")
    @FormUrlEncoded
    fun withDrawCoin(
        @Field("api_key") api_key: String,
        @Field("amounts") amounts: String,
        @Field("to_addresses") to_addresses: String
    ): Single<Balance>


    @GET("get_transactions/")
    fun getTransaction(@Query("api_key") api_key: String, @Query("type") type: String): Single<Balance>
}