package com.cheise_proj.e_commerce.ui.modal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.data.service.FilterService
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_modal_size.*

/**
 * A simple [Fragment] subclass.
 */
class ModalSizeFragment : BottomSheetDialogFragment() {
    companion object {
        const val MODAL_SIZE_TAG = "ModalSizeFragment"
        fun newInstance() =
            ModalSizeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modal_size, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applySizeChips(FilterService.getSizesOpts())
    }

    private fun applySizeChips(sizesOpts: Array<String>) {
        for (i in sizesOpts.indices) {
            val chip = layoutInflater.inflate(R.layout.chip_single, chip_group, false) as Chip
            chip.text = sizesOpts[i]
            chip_group.addView(chip)
        }
    }

}
