package com.rocksolidapps.cake.ui.cakes

import android.os.Bundle
import com.rocksolidapps.cake.R
import com.rocksolidapps.cake.ui.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CakesActivity : BaseActivity() {
    val viewModel: CakesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cakes)
        setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.cakes)
    }
}