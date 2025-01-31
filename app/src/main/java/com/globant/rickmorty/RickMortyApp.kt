package com.globant.rickmorty

import android.app.Application
import com.globant.rickmorty.di.dataModule
import com.globant.rickmorty.di.viewModeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class RickMortyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@RickMortyApp)
            modules(dataModule, viewModeModule)
        }
    }
}