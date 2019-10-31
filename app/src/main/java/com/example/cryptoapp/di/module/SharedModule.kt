package com.example.cryptoapp.di.module

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.cryptoapp.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedModule(private val application: BaseApplication) {

    @Provides
    fun context(): Context {
        return application
    }

    @Provides
    @Singleton
    fun sharedPerf(context: Context): SharedPreferences {
        return context.getSharedPreferences("API", MODE_PRIVATE)
    }

}