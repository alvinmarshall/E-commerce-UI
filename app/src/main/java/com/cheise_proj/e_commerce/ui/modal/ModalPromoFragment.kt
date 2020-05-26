package com.cheise_proj.e_commerce.ui.modal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cheise_proj.e_commerce.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * A simple [Fragment] subclass.
 */
class ModalPromoFragment : BottomSheetDialogFragment() {
    companion object {
        fun newInstance() = ModalPromoFragment()
        const val MODAL_PROMO_TAG = "ModalPromoFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modal_promo, container, false)
    }

}
