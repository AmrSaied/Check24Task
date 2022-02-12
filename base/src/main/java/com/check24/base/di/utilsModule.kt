package com.check24.base.di

import com.check24.base.utils.NetworkHelper
import org.koin.dsl.module

/**
 * Koin utils module
 */
val utilsModule = module {


    single { NetworkHelper() }
}
