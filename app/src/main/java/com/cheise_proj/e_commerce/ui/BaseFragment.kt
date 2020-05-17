package com.cheise_proj.e_commerce.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cheise_proj.e_commerce.di.module.glide.GlideApp
import com.cheise_proj.e_commerce.factory.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.toolbar_collapse_with_button.*
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {
    @Inject
    lateinit var factory: ViewModelFactory
    protected val handler = Handler(Looper.getMainLooper())

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        getToolbarImage()?.let { image ->
                if (getImageUrl() != null) {

                    GlideApp.with(view.context).load(getImageUrl())
                        .diskCacheStrategy(DiskCacheStrategy.NONE).into(image)
                }

        }

    }


}