package com.example.cryptoapp.di

import com.example.cryptoapp.BaseApplication
import com.example.cryptoapp.MainActivity
import com.example.cryptoapp.di.module.NetworkModule
import com.example.cryptoapp.di.module.SharedModule
import com.example.cryptoapp.ui.fragment.Account
import com.example.cryptoapp.ui.fragment.Balance
import com.example.cryptoapp.ui.fragment.Home
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedModule::class, NetworkModule::class])
interface SharedComponent {

    fun inject(application: BaseApplication)
    fun inject(application: MainActivity)
    fun inject(fragment: Home)
    fun inject(fragment: Account)
    fun inject(fragment: Balance)
}