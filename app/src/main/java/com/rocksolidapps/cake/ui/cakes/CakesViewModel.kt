package com.rocksolidapps.cake.ui.cakes

import androidx.lifecycle.ViewModel
import com.rocksolidapps.cake.api.CakeApi

class CakesViewModel(private val cakeApi: CakeApi) : ViewModel() {

    fun fetchCakes() {
        // TODO: show some progress
        // TODO: add error handling
        cakeApi.fetchCakes()
            .subscribe { response ->
            }
    }
}