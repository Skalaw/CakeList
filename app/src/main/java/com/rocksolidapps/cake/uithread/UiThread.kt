package com.rocksolidapps.cake.uithread

import io.reactivex.Scheduler

object UiThread {
    private var executionThread: ExecutionThread? = null

    fun init(executionThread: ExecutionThread) {
        UiThread.executionThread = executionThread
    }

    fun uiScheduler(): Scheduler {
        if (executionThread == null) {
            throw NotImplementedThreadException()
        }
        return executionThread!!.uiScheduler()
    }
}