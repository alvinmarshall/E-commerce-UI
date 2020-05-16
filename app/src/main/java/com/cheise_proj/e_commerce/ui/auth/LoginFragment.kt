package com.cheise_proj.e_commerce.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cheise_proj.e_commerce.R
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        navigateToHomePage()

        btn_login.setOnClickListener { navigateToHomePage() }
        btn_forgot_password.setOnClickListener { navigateToForgotPasswordPage() }
    }

    private fun navigateToForgotPasswordPage() {
        val action = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
        findNavController().navigate(action)
    }


    private fun navigateToHomePage() {
        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        findNavController().navigate(action)
    }

}
