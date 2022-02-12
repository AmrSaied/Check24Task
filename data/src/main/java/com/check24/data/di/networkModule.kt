package com.check24.data.di



import com.check24.data.BuildConfig
import com.check24.data.constant.BASE_API_URL
import com.check24.data.constant.DEFAULT_NETWORK_TIMEOUT
import com.check24.data.network.RetrofitExecutor
import com.check24.data.network.api.Check24ApiService
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Koin module for okHttp , retrofit and it`s api interfaces
 */
val networkModule = module(override = true) {

    fun provideOkHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE }




    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(DEFAULT_NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(DEFAULT_NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
        .build()
    }


    fun provideRetrofit(baseUrl:String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    single(named(DI_BASE_URL)) { BASE_API_URL }

    single { provideOkHttpLoggingInterceptor() }

    single { provideOkHttpClient(get()) }

    single { provideRetrofit(get(named(DI_BASE_URL)),get()) }

    factory { RetrofitExecutor(androidContext()) }

    factory { get<Retrofit>().create(Check24ApiService::class.java) }


}

const val DI_BASE_URL = "baseUrl"
