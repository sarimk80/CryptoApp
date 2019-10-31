package com.example.cryptoapp

import android.app.Application
import com.example.cryptoapp.di.DaggerSharedComponent
import com.example.cryptoapp.di.SharedComponent
import com.example.cryptoapp.di.module.SharedModule

class BaseApplication : Application() {

    lateinit var component: SharedComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerSharedComponent.builder().sharedModule(
            SharedModule(
                this
            )
        ).build()
    }

    fun getSharedComponent(): SharedComponent {
        return component
    }
}