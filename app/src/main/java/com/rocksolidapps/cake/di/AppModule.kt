package com.rocksolidapps.cake.di

import com.rocksolidapps.cake.ui.cakes.CakesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { CakesViewModel(get()) }
}