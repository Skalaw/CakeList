package com.rocksolidapps.cake.api

import com.rocksolidapps.cake.api.repository.CakeRepository
import com.rocksolidapps.cake.api.rest.CakeRest
import com.rocksolidapps.cake.extensions.applySchedulers

// TODO: for REST things, we can move to seperate module app
class CakeApi(serviceProvider: ServiceProvider) : CakeRepository {
    private val cakeRepository = serviceProvider.getService(CakeRest::class.java)

    override fun fetchCakes() = cakeRepository.fetchCakes().applySchedulers()
}