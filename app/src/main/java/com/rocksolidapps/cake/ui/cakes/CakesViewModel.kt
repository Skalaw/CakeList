package com.rocksolidapps.cake.ui.cakes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.rocksolidapps.cake.api.CakeApi
import com.rocksolidapps.cake.api.model.Cake

class CakesViewModel(private val cakeApi: CakeApi) : ViewModel() {
    val cakeLiveData = MutableLiveData<List<Cake>>()

    fun fetchCakes() {
        // TODO: show some progress
        // TODO: add error handling
        cakeApi.fetchCakes()
            .subscribe { response ->
                cakeLiveData.postValue(response)
            }
    }
}