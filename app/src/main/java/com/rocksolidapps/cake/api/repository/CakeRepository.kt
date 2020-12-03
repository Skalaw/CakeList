package com.rocksolidapps.cake.api.repository

import com.rocksolidapps.cake.api.model.Cake
import io.reactivex.Observable

interface CakeRepository {
    fun fetchCakes(): Observable<List<Cake>>
}