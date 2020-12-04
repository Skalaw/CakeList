package com.rocksolidapps.cake.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rocksolidapps.cake.model.ErrorEvent
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

open class BaseViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val retrySubject = PublishSubject.create<String>()

    val errorLiveData = MutableLiveData<ErrorEvent>()

    override fun onCleared() {
        compositeDisposable.clear()
    }

    protected fun <T> applyBinding(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.doOnSubscribe { disposable: Disposable? ->
                disposable?.let { bindToLifecycle(disposable) }
            }
        }
    }

    fun retryLastRequest() {
        retrySubject.onNext("")
    }

    private fun bindToLifecycle(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun <T> applyErrorHandling(): ObservableTransformer<T, T> = ObservableTransformer { upstream ->
        upstream.doOnError { throwable -> // TODO: here we can extended to support error types
            errorLiveData.value = ErrorEvent(throwable)
        }.retryWhen { repeatHandler ->
            repeatHandler.flatMap { retrySubject.hide() }
        }
    }
}