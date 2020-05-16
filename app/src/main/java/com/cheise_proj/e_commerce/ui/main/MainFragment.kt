package com.cheise_proj.e_commerce.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.factory.ViewModelFactory
import com.cheise_proj.e_commerce.ui.BaseFragment
import kotlinx.android.synthetic.main.toolbar_collapse_with_button.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : BaseFragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun getToolBar(): Toolbar? = toolbar
    override fun showButtonOnToolbar(): Boolean = true
    override fun getToolbarImage(): ImageView? = app_bar_image
    override fun getImageUrl(): String? = "https://picsum.photos/400/700/?blur=2"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar_button.setOnClickListener { navigateToMain2Page() }
        configViewModel()
    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        viewModel.getProducts()
    }


    private fun navigateToMain2Page() {
        val action = MainFragmentDirections.actionMainFragmentToMain2Fragment()
        findNavController().navigate(action)


    }


}
