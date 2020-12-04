package com.rocksolidapps.cake.uithread

import io.reactivex.Scheduler

interface ExecutionThread {
    fun uiScheduler(): Scheduler
}