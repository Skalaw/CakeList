package com.rocksolidapps.cake.ui.cakes.usecase

import com.rocksolidapps.cake.api.CakeApi

class FetchCakesUseCase(val api: CakeApi) {
    fun execute() = api.fetchCakes()
}