package com.rocksolidapps.cake.api

interface ServiceProvider {
    fun <T> getService(serviceClass: Class<T>): T
}