package com.rocksolidapps.cake.ui.cakes

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import com.rocksolidapps.cake.R
import com.rocksolidapps.cake.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_cakes.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CakesActivity : BaseActivity() {
    val viewModel: CakesViewModel by viewModel()

    private lateinit var cakeAdapter: CakeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cakes)
        initView()

        viewModel.cakeLiveData.observe(this, { list ->
            cakeAdapter.submitList(list)
        })

        if (savedInstanceState == null) {
            viewModel.fetchCakes()
        }
    }

    private fun initView() {
        setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.cakes)
        cakeAdapter = CakeAdapter()
        with(rvCake) {
            adapter = cakeAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }
}