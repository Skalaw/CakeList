package com.rocksolidapps.cake.ui.cakes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.rocksolidapps.cake.api.model.Cake
import com.rocksolidapps.cake.tools.MockApi
import com.rocksolidapps.cake.tools.getOrAwaitValue
import com.rocksolidapps.cake.uithread.ExecutionThread
import com.rocksolidapps.cake.uithread.UiThread
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CakesViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        UiThread.init(object : ExecutionThread {
            override fun uiScheduler() = Schedulers.io()
        })
    }

    @Test
    fun `app start successfully`() {
        val viewModel = CakesViewModel(MockApi().getCakeApi(getMockCake()))

        viewModel.fetchCakes()
        val cakes = viewModel.cakeLiveData.getOrAwaitValue()

        Assert.assertNotEquals(cakes, null)
        Assert.assertEquals(cakes.size, 3)
        Assert.assertEquals(cakes[0].title, "Cake1")
        Assert.assertEquals(cakes[1].title, "Cake2")
        Assert.assertEquals(cakes[2].title, "Cake3")
    }

    @Test
    fun `app start with error list`() {
        val viewModel = CakesViewModel(MockApi().getCakeApiError(getMockCake()))

        viewModel.fetchCakes()
        val errorEvent = viewModel.errorLiveData.getOrAwaitValue()

        Assert.assertEquals(errorEvent.throwable.message, "Connection error")
    }

    private fun getMockCake() = listOf(
        Cake("Cake1", "Description 1", "url"),
        Cake("Cake1", "Description 1", "url"),
        Cake("Cake3", "Description 3", "url"),
        Cake("Cake2", "Description 2", "url"),
        Cake("Cake1", "Description 1", "url"),
        Cake("Cake2", "Description 2", "url")
    )
}