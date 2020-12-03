package com.rocksolidapps.cake.extensions

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.applySchedulers(): Observable<T> = observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())