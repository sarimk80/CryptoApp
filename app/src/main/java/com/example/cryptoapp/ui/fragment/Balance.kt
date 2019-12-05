package com.example.cryptoapp.ui.fragment


import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.cryptoapp.BaseApplication
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.FragmentBalanceBinding
import com.example.cryptoapp.viewmodels.TransactionViewModel
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class Balance : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var transactionViewModel: TransactionViewModel

    private lateinit var fragmentBalanceBinding: FragmentBalanceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity!!.application as BaseApplication).getSharedComponent().inject(this)

        fragmentBalanceBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_balance, container, false)
        // Inflate the layout for this fragment
        return fragmentBalanceBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val apiKey = sharedPreferences.getString("APIKEY", "0000") ?: ""

        transactionViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(TransactionViewModel::class.java)

        transactionViewModel.errorMessage().observe(this, Observer { error ->
            fragmentBalanceBinding.errorTxt.text = error
        })

        transactionViewModel.isLoading().observe(this, Observer { isLoading ->
            if (isLoading) {
                fragmentBalanceBinding.progresBar.visibility = View.VISIBLE
            } else {
                fragmentBalanceBinding.progresBar.visibility = View.INVISIBLE
            }
        })

        transactionViewModel.getTransactionMutableLiveData(apiKey, "sent")
            .observe(this, Observer { data ->
                fragmentBalanceBinding.balance = data
                Log.d("Transaction", data.toString())
            })
    }
}
