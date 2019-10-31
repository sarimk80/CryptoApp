package com.example.cryptoapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoapp.model.Balance
import com.example.cryptoapp.repository.BalanceRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val balanceRepo: BalanceRepo) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val balanceMutableLiveData = MutableLiveData<Balance>()

    private val isLoading = MutableLiveData<Boolean>()
    private val errorDisplay = MutableLiveData<String>()


    fun getBalanceMutableliveData(apiKey: String): MutableLiveData<Balance> {

        loadData(apiKey)
        return balanceMutableLiveData
    }

    private fun loadData(apiKey: String) {

        isLoading.value = true
        disposable.add(
            balanceRepo.getBalance_Repo(apiKey).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({ data ->
                    getBalanceMutableliveData(apiKey).value = data
                    isLoading.value = false
                }, { error ->
                    isLoading.value = false
                    Log.e("Home", error.toString())
                })
        )
    }

    fun isLoading(): LiveData<Boolean> {
        return isLoading
    }

    fun errorDisplay(): LiveData<String> {
        return errorDisplay
    }


}