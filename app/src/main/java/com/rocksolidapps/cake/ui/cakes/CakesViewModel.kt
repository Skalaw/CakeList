package com.rocksolidapps.cake.ui.cakes

import androidx.lifecycle.MutableLiveData
import com.rocksolidapps.cake.api.CakeApi
import com.rocksolidapps.cake.api.model.Cake
import com.rocksolidapps.cake.ui.BaseViewModel

class CakesViewModel(private val cakeApi: CakeApi) : BaseViewModel() {
    val cakeLiveData = MutableLiveData<List<Cake>>()

    fun fetchCakes() {
        // TODO: show some progress
        cakeApi.fetchCakes()
            .compose(applyErrorHandling())
            .compose(applyBinding())
            .subscribe { response ->
                val sortedAndNoDuplicateList = response.distinctBy { it.title }.sortedBy { it.title }
                cakeLiveData.postValue(sortedAndNoDuplicateList)
            }
    }
}