package com.cheise_proj.e_commerce.ui.product

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.di.module.glide.GlideApp
import com.cheise_proj.e_commerce.factory.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.toolbar_collapse_with_button.*
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel> : DaggerFragment() {
    @Inject
    lateinit var factory: ViewModelFactory
    protected val handler = Handler(Looper.getMainLooper())
    protected lateinit var viewModel: VM

    private fun initToolbar() {
        val navHostFragment = findNavController()
        val toolbar = getToolBar()
        toolbar?.setupWithNavController(navController = navHostFragment)
        toolbar?.title = ""
        toolbar_text.text = getToolBarText()
        setToolbarButtonVisibility()
    }

    private fun setToolbarButtonVisibility() {
        if (showButtonOnToolbar()) {
            toolbar_button.visibility = View.VISIBLE
            toolbar_button.text = getToolBarButtonText()
        } else {
            toolbar_button.visibility = View.GONE
        }
    }

    open fun getToolBar(): Toolbar? = null
    open fun getToolbarImage(): ImageView? = null
    open fun getImageUrl(): String? = null
    open fun showButtonOnToolbar(): Boolean = false
    open fun getToolBarText(): String? = "Base Text"
    open fun getToolBarButtonText(): String? = "Base action"
    abstract fun getViewModel(): Class<VM>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        getToolbarImage()?.let { image ->
            if (getImageUrl() != null) {

                GlideApp.with(view.context).load(getImageUrl())
                    .diskCacheStrategy(DiskCacheStrategy.NONE).into(image)
            }

        }
        viewModel = ViewModelProvider(this, factory)[getViewModel()]
    }

    protected fun showNoData(view: View) {
        Snackbar.make(view, getString(R.string.no_data_msg), Snackbar.LENGTH_LONG).show()
    }

    protected fun snackMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }


}