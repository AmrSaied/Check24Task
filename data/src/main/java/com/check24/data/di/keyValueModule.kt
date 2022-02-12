package com.check24.data.di



import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.check24.data.constant.PREF_NAME
import com.check24.data.keyValue.KeyValueStore
import com.check24.data.keyValue.KeyValueStoreSharedPrefImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module



val keyValueModule = module {

    single<KeyValueStore>{ KeyValueStoreSharedPrefImpl(provideSharedPreference(androidContext())) }
}

fun provideSharedPreference(context: Context): SharedPreferences {
    return context.getSharedPreferences(PREF_NAME,MODE_PRIVATE)
}
