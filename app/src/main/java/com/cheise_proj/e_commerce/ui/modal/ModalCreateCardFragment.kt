package com.cheise_proj.e_commerce.ui.modal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.data.db.entity.CardEntity
import com.cheise_proj.e_commerce.ui.bag.adapter.CardAdapter.Companion.CARD_MASTER
import com.cheise_proj.e_commerce.utils.ItemClickListener
import com.cheise_proj.e_commerce.utils.MASTER_CARD_LOGO_URL
import com.cheise_proj.e_commerce.utils.VISA_CARD_LOGO_URL
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_modal_create_card.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class ModalCreateCardFragment : BottomSheetDialogFragment() {

    companion object {
        const val MODAL_CREATE_CARD_TAG = "ModalCreateCardFragment"
        fun newInstance() =
            ModalCreateCardFragment()
    }

    private var isDefault = 0
    private var itemClickListener: ItemClickListener<CardEntity>? = null

    internal fun itemCallback(callback: ItemClickListener<CardEntity>) {
        itemClickListener = callback
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modal_create_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_add_card.setOnClickListener { createPaymentCard() }
        cb_default.setOnCheckedChangeListener { _, isChecked ->
            isDefault = if (isChecked) 1 else 0
        }
    }

    private fun createPaymentCard() {
        val name = et_name.text.toString()
        val cardNumber = et_card_number.text.toString()
        val expireDate = et_expired_date.text.toString()
        val cvv = et_cvv.text.toString()
        val type = et_card_type.text.toString()
        val image = if (type.trim().toLowerCase(Locale.ROOT)
                .contains(CARD_MASTER)
        ) MASTER_CARD_LOGO_URL else VISA_CARD_LOGO_URL
        val card = CardEntity(0, name, cardNumber, expireDate, cvv, isDefault, type, image)
        itemClickListener?.onClick(card)
        dismiss()
    }


}
