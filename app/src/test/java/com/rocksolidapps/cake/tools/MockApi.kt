package com.rocksolidapps.cake.tools

import com.rocksolidapps.cake.api.CakeApi
import com.rocksolidapps.cake.api.ServiceProvider
import com.rocksolidapps.cake.api.model.Cake
import com.rocksolidapps.cake.api.rest.CakeRest
import io.reactivex.Observable

class MockApi {
    fun getCakeApi(mockedList: List<Cake>) = CakeApi(object : ServiceProvider {
        override fun <T> getService(serviceClass: Class<T>): T {
            return object : CakeRest {
                override fun fetchCakes(): Observable<List<Cake>> = Observable.just(mockedList)
            } as T
        }
    })

    fun getCakeApiError(mockedList: List<Cake>) = CakeApi(object : ServiceProvider {
        override fun <T> getService(serviceClass: Class<T>): T {
            return object : CakeRest {
                override fun fetchCakes(): Observable<List<Cake>> = Observable.create { emitter ->
                    throw Throwable("Connection error")
                }
            } as T
        }
    })
}