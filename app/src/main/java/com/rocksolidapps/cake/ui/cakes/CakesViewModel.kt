package com.rocksolidapps.cake.ui.cakes

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.rocksolidapps.cake.api.CakeApi
import com.rocksolidapps.cake.api.model.Cake
import com.rocksolidapps.cake.ui.BaseViewModel
import com.rocksolidapps.cake.ui.cakes.usecase.FetchCakesUseCase

class CakesViewModel(private val cakeApi: CakeApi) : BaseViewModel() {
    val cakeLiveData = MutableLiveData<List<Cake>>()

    @SuppressLint("CheckResult") // result of subscribe in applyBinding
    fun fetchCakes() {
        // TODO: show some progress
        FetchCakesUseCase(cakeApi).execute()
            .compose(applyErrorHandling())
            .compose(applyBinding())
            .subscribe { response ->
                val sortedAndNoDuplicateList = response.distinctBy { it.title }.sortedBy { it.title }
                cakeLiveData.postValue(sortedAndNoDuplicateList)
            }
    }
}