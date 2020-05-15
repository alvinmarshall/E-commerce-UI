package com.cheise_proj.e_commerce.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.toolbar_collapse_with_button.*

abstract class BaseFragment : Fragment() {
    private fun initToolbar() {
        val navHostFragment = findNavController()
        val toolbar = getToolBar()
        toolbar?.setupWithNavController(navController = navHostFragment)
        toolbar?.title = ""
        setToolbarButtonVisibility()
    }

    private fun setToolbarButtonVisibility() {
        if (showButtonOnToolbar()) {
            toolbar_button.visibility = View.VISIBLE
        } else {
            toolbar_button.visibility = View.GONE
        }
    }

    open fun getToolBar(): Toolbar? = null
    open fun getToolbarImage(): ImageView? = null
    open fun getImageUrl(): String? = null
    open fun showButtonOnToolbar(): Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        getToolbarImage()?.let { image ->
            if (getImageUrl() != null) {

                Glide.with(view.context).load(getImageUrl()).into(image)
            }

        }

    }


}