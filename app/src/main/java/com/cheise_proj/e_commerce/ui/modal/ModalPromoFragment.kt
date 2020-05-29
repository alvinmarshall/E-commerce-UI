package com.cheise_proj.e_commerce.ui.modal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.model.PromoCode
import com.cheise_proj.e_commerce.ui.modal.adapter.PromoAdapter
import com.cheise_proj.e_commerce.utils.ItemClickListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.enter_promo_code.*
import kotlinx.android.synthetic.main.fragment_modal_promo.*

/**
 * A simple [Fragment] subclass.
 */
class ModalPromoFragment : BottomSheetDialogFragment() {
    companion object {
        fun newInstance() = ModalPromoFragment()
        const val MODAL_PROMO_TAG = "ModalPromoFragment"
    }

    private lateinit var viewModel: ModelViewModel
    private lateinit var adapter: PromoAdapter
    private var itemClickListener: ItemClickListener<String>? = null

    internal fun setClickCallback(callback: ItemClickListener<String>) {
        itemClickListener = callback
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modal_promo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PromoAdapter()
        adapter.apply {
            setItemClickCallback(object : ItemClickListener<String?> {
                override fun onClick(data: String?) {
                    tv_promo_code.text = data
                }
            })
        }
        fab_send.setOnClickListener {
            itemClickListener?.onClick(tv_promo_code.text.toString())
            dismiss()
        }
        initRecyclerView()
        configViewModel()
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
        }

    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this)[ModelViewModel::class.java]
        viewModel.getPromoCodes()
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.promoList.observe(viewLifecycleOwner, Observer { data -> loadPromoList(data) })
    }

    private fun loadPromoList(data: List<PromoCode>?) {
        adapter.submitList(data)
        recycler_view.adapter = adapter

    }

    override fun onDestroyView() {
        recycler_view.adapter = null
        super.onDestroyView()
    }

}
