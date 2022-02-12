package com.check24.task.app

import androidx.multidex.MultiDexApplication
import com.check24.base.di.utilsModule
import com.check24.data.di.keyValueModule
import com.check24.data.di.networkModule
import com.check24.data.di.repositoryModule
import com.check24.task.di.allFeatures
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.util.*
import kotlin.collections.ArrayList

/**
 * Custom app class to initialize koin dependencies graph
 */
class Check24App : MultiDexApplication() {


    override fun onCreate() {
        super.onCreate()
        initKoin()
    }



    private fun initKoin() {
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@Check24App)
            val modules = ArrayList(allFeatures)
            modules.addAll(listOf(utilsModule ,networkModule, repositoryModule,keyValueModule))
            modules(
                modules
            )
        }
    }



}