package com.example.cryptoapp.model

data class Balance(
    val status: String,
    val data: Data
)

data class Data(
    val error_message: String?,
    val network: String?,
    val available_balance: String?,
    val pending_received_balance: String?,
    val txs:Array<String>?
)


//{
//    "status" : "success",
//    "data" : {
//    "network" : "BTCTEST",
//    "available_balance" : "0.0",
//    "pending_received_balance" : "0.0"
//}
//}

//{
//    "status": "fail",
//    "data": {
//    "error_message": "One or more destination addresses are invalid for Network=BTCTEST."
//}
//}