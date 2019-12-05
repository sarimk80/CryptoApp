package com.example.cryptoapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoapp.model.Balance
import com.example.cryptoapp.repository.TransactionRepo
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TransactionViewModel @Inject constructor(private val transactionRepo: TransactionRepo) :
    ViewModel() {

    private val disposable = CompositeDisposable()
    private val transactionMutableLiveData = MutableLiveData<Balance>()
    private val isLoading = MutableLiveData<Boolean>()
    private val errorMessage = MutableLiveData<String>()


    fun getTransactionMutableLiveData(api_key: String, type: String): MutableLiveData<Balance> {

        loadData(api_key, type)
        return transactionMutableLiveData
    }

    private fun loadData(api_key: String, type: String) {
        isLoading.value = true

        disposable.add(
            transactionRepo.getTransactionRepo(
                api_key,
                type
            ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ data ->
                getTransactionMutableLiveData(api_key, type).value = data
                isLoading.value = false
            },
                { error ->
                    isLoading.value = false
                    Log.d("Transaction", error.message.toString())
                })
        )
    }

    fun isLoading(): LiveData<Boolean> {
        return isLoading
    }

    fun errorMessage(): LiveData<String> {
        return errorMessage
    }
}