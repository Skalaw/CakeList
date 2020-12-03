package com.rocksolidapps.cake.api.network

import com.rocksolidapps.cake.api.ServiceProvider
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitServiceProvider(baseUrl: String, moshi: Moshi, httpClient: OkHttpClient) : ServiceProvider {
    private val retrofit = createRetrofit(baseUrl, moshi, httpClient)

    override fun <T> getService(serviceClass: Class<T>): T = retrofit.create(serviceClass)
    private fun createRetrofit(baseUrl: String, moshi: Moshi, httpClient: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}