package com.cheise_proj.e_commerce.ui.category

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.cheise_proj.e_commerce.factory.ViewModelFactory
import com.cheise_proj.e_commerce.ui.category.adapter.CategoryImageAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {
    @Inject
    lateinit var factory: ViewModelFactory
    protected lateinit var viewModel: CategoryViewModel
    protected lateinit var adapter: CategoryImageAdapter

    protected val handler = Handler(Looper.getMainLooper())

    open fun getHeaderText(): String? = "Summer Sales"
    open fun getSubHeaderText(): String? = "Up to 50% off"
    open fun getHeaderTextView(): TextView? = null
    open fun getSubHeaderTextView(): TextView? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CategoryImageAdapter()
        viewModel = ViewModelProvider(this, factory)[CategoryViewModel::class.java]
        viewModel.loadCategories()
        initBannerView()
    }

    private fun initBannerView() {
        getHeaderTextView()?.text = getHeaderText()
        getSubHeaderTextView()?.text = getSubHeaderText()
    }
}