package com.cheise_proj.e_commerce.ui.modal

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.data.service.FilterService
import com.cheise_proj.e_commerce.di.module.glide.GlideApp
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_modal_color.*

/**
 * A simple [Fragment] subclass.
 */
class ModalColorFragment : BottomSheetDialogFragment() {
    companion object {
        const val MODAL_Color_TAG = "ModalColorFragment"
        fun newInstance() =
            ModalColorFragment()

        private const val PADDING = 5
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modal_color, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applySizeChips(FilterService.getColorOpts(requireContext()))
    }

    private fun applySizeChips(colorOpts: Array<Int>) {
        for (i in colorOpts.indices) {
            val imageView = LayoutInflater.from(context)
                .inflate(R.layout.circular_img, color_group, false) as ImageView
            imageView.setPadding(PADDING, 0, PADDING, 0)
            val colorDrawable = ColorDrawable(colorOpts[i])
            GlideApp.with(requireContext()).load(colorDrawable).circleCrop().into(imageView)
            color_group.addView(imageView)
        }
    }

}
