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
import com.example.cryptoapp.databinding.FragmentAccountBinding
import com.example.cryptoapp.viewmodels.AccountViewModel
import com.example.cryptoapp.viewmodels.ViewModelFactory
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class Account : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var accountViewModel: AccountViewModel

    private lateinit var fragmentAccountBinding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity!!.application as BaseApplication).getSharedComponent().inject(this)

        fragmentAccountBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)
        // Inflate the layout for this fragment
        return fragmentAccountBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiKey = sharedPreferences.getString("APIKEY", "0000") ?: ""

        accountViewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        ).get(AccountViewModel::class.java)


        accountViewModel.errorDisplay().observe(this, Observer { error ->
            fragmentAccountBinding.errorTxt.text = error
        })

        accountViewModel.isLoading().observe(this, Observer { isLoading ->
            if (isLoading) {
                fragmentAccountBinding.progresBar.visibility = View.VISIBLE
            } else {
                fragmentAccountBinding.progresBar.visibility = View.INVISIBLE
            }
        })

        //Error 404 because there is no coin in the account
        accountViewModel.getWithDrawCoinRepoLiveData(apiKey, "100", "3D98HvyfAdPa2FsoeXpukHN8TvdFW5VtjZ").observe(this,
            Observer { data ->
                Log.e("Account", data.status)
            })
    }
}
