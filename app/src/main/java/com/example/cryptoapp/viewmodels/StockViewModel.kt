package com.example.cryptoapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoapp.cryptomodel.CryptoModel
import com.example.cryptoapp.service.CryptoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class StockViewModel : ViewModel() {

    private val mutableCryptoData = MutableLiveData<CryptoModel>()

    fun getMutableCryptoData(): LiveData<CryptoModel> {
        loadData()
        return mutableCryptoData

    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://min-api.cryptocompare.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val cryptoInterface: CryptoInterface = retrofit.create(CryptoInterface::class.java)

        val call: Call<CryptoModel> = cryptoInterface.getData()

        call.enqueue(object : Callback<CryptoModel> {
            override fun onFailure(call: Call<CryptoModel>, t: Throwable) {
                Log.d("Crypto", t.message.toString())
            }

            override fun onResponse(call: Call<CryptoModel>, response: Response<CryptoModel>) {
                mutableCryptoData.value = response.body()
            }

        })
    }
}