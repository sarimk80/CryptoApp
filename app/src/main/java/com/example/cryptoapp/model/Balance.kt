package com.example.cryptoapp.model

data class Balance(
    val status: String, val data: Data
)

data class Data(
    val network: String,
    val available_balance: String,
    val pending_received_balance: String
)


//{
//    "status" : "success",
//    "data" : {
//    "network" : "BTCTEST",
//    "available_balance" : "0.0",
//    "pending_received_balance" : "0.0"
//}
//}