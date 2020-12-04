package com.rocksolidapps.cake.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.rocksolidapps.cake.R
import com.rocksolidapps.cake.model.ErrorEvent

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {
    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.errorLiveData.observe(this, { errorEvent -> onShowErrorSnackbar(errorEvent) })
    }

    private fun onShowErrorSnackbar(errorEvent: ErrorEvent) {
        val message = getString(R.string.genericError) + errorEvent.throwable.message
        val view: View = findViewById(android.R.id.content)
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).apply {
            setAction(R.string.retry) { viewModel.retryLastRequest() }
            show()
        }
    }
}