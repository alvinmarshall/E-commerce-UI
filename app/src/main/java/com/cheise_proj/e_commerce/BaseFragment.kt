package com.cheise_proj.e_commerce

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cheise_proj.e_commerce.factory.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel> : DaggerFragment() {
    @Inject
    lateinit var factory: ViewModelFactory
    protected val handler = Handler(Looper.getMainLooper())
    protected lateinit var viewModel: VM
    abstract fun getViewModel(): Class<VM>
    open fun getToolBar(): Toolbar? = null

    private fun initToolbar() {
        val navHostFragment = findNavController()
        val toolbar = getToolBar()
        toolbar?.setupWithNavController(navController = navHostFragment)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this, factory)[getViewModel()]
    }


}