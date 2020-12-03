package com.rocksolidapps.cake.di

import com.rocksolidapps.cake.BuildConfig
import com.rocksolidapps.cake.api.CakeApi
import com.rocksolidapps.cake.api.network.RetrofitServiceProvider
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

const val ENDPOINT = "https://gist.githubusercontent.com/"

val restModule = module {
    single { getOkHttpClient() }
    single { getMoshi() }
    single { RetrofitServiceProvider(ENDPOINT, get(), get()) }
    single { CakeApi(get()) }
}

private fun getOkHttpClient() = OkHttpClient.Builder().apply {
    if (BuildConfig.DEBUG) {
        addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
    }
    connectTimeout(45, TimeUnit.SECONDS)
    readTimeout(45, TimeUnit.SECONDS)
    writeTimeout(45, TimeUnit.SECONDS)
}.build()

private fun getMoshi() = Moshi.Builder().build()