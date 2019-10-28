package com.example.cryptoapp.di

import com.example.cryptoapp.BaseApplication
import com.example.cryptoapp.MainActivity
import com.example.cryptoapp.ui.fragment.Home
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SharedModule::class])
interface SharedComponent {

    fun inject(application: BaseApplication)
    fun inject(application:MainActivity)
    fun inject(fragment:Home)
}