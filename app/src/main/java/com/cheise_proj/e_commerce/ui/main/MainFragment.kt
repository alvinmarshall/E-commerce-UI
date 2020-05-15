package com.cheise_proj.e_commerce.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.factory.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var viewModel: MainViewModel


    companion object {
        fun newInstance() = MainFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configViewModel()

    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
//        viewModel.registerNewAccount("kelvin@me.com","123456","")
        viewModel.loginWithEmail("kelvin@me.com", "123456")
    }

}
