package com.marat.retrofittest

import android.app.Application
import com.marat.retrofittest.di.appModule
import com.marat.retrofittest.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class RikApplication: Application(){


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@RikApplication)
            modules(listOf(appModule, networkModule))
        }

    }

}