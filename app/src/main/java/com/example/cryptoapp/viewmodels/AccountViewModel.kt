package com.example.cryptoapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoapp.model.Balance
import com.example.cryptoapp.repository.WithDrawCoinRepo
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AccountViewModel @Inject constructor(private val withDrawCoinRepo: WithDrawCoinRepo) :
    ViewModel() {

    private val disposable = CompositeDisposable()
    private val withDrawCoinRepoLiveData = MutableLiveData<Balance>()

    private val isLoading = MutableLiveData<Boolean>()
    private val errorDisplay = MutableLiveData<String>()

    fun getWithDrawCoinRepoLiveData(
        api_key: String,
        amount: String,
        to_address: String
    ): MutableLiveData<Balance> {
        loadData(api_key, amount, to_address)
        return withDrawCoinRepoLiveData
    }

    private fun loadData(api_key: String, amount: String, to_address: String) {

        isLoading.value = true
        errorDisplay.value = ""

        disposable.add(
            withDrawCoinRepo.getWithDrawCoinRepo(
                api_key,
                amount,
                to_address
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({ data ->
                    isLoading.value = false
                    getWithDrawCoinRepoLiveData(api_key, amount, to_address).value = data
                }, { error ->
                    isLoading.value = false
                    errorDisplay.value = error.message
                    Log.e("Account", error.message + " " + error.toString())
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