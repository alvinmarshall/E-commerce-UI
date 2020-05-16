package com.cheise_proj.e_commerce.ui.auth

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cheise_proj.e_commerce.R
import kotlinx.android.synthetic.main.fragment_forgot_password.*

/**
 * A simple [Fragment] subclass.
 */
class ForgotPasswordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_send.setOnClickListener { sendPasswordRequest() }
    }

    private fun sendPasswordRequest() {
        val email = et_email.text.toString()
        if (TextUtils.isEmpty(email)){
            tl_email.error = "invalid email"
        }
    }

}
