package com.rocksolidapps.cake.ui.cakes

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import com.rocksolidapps.cake.R
import com.rocksolidapps.cake.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_cakes.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CakesActivity : BaseActivity<CakesViewModel>() {
    override val viewModel: CakesViewModel by viewModel()

    private lateinit var cakeAdapter: CakeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cakes)
        initView()

        viewModel.cakeLiveData.observe(this, { list ->
            swipeRefresh.isRefreshing = false
            cakeAdapter.submitList(list)
        })

        if (savedInstanceState == null) {
            viewModel.fetchCakes()
        }
    }

    private fun initView() {
        setSupportActionBar(toolbar)
        cakeAdapter = CakeAdapter()
        with(rvCake) {
            adapter = cakeAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            // TODO: ListAdapter have default animation for add items, remove items, change content (see: CakeAdapter -> CakeDiffCallback), to check reaction just change json object from http. If you wanna see more animations, just need to implement itemAnimator here instead Default animations
        }
        swipeRefresh.setOnRefreshListener { viewModel.fetchCakes() }
    }
}