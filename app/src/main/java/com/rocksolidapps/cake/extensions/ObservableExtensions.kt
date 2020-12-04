package com.rocksolidapps.cake.extensions

import com.rocksolidapps.cake.uithread.UiThread
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.applySchedulers(): Observable<T> = observeOn(UiThread.uiScheduler()).subscribeOn(Schedulers.io())