package com.cheise_proj.e_commerce.ui.bag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cheise_proj.e_commerce.R
import kotlinx.android.synthetic.main.fragment_success.*

/**
 * A simple [Fragment] subclass.
 */
class SuccessFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_success, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_continue.setOnClickListener {  navigateToContinuePage()}
    }

    private fun navigateToContinuePage() {
        val action = SuccessFragmentDirections.actionSuccessFragmentToSuccess2Fragment()
        findNavController().navigate(action)

    }

}
