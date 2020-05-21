package com.cheise_proj.e_commerce.ui.modal

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView

import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.utils.ItemClickListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_modal_sort.*

/**
 * A simple [Fragment] subclass.
 */
class ModalSortFragment : BottomSheetDialogFragment() {
    companion object {
        const val MODAL_SORT_TAG = "ModalSortFragment"
        fun newInstance() =
            ModalSortFragment()

        val SORT_MODAL_ARRAY = arrayOf(
            "Popular", "Newest", "Customer review", "Price: lower to high", "Price: highest to low"
        )
    }

    private var itemClickListener: ItemClickListener<Int>? = null
    private lateinit var arrayAdapter: ArrayAdapter<*>
    internal fun setItemCallback(callback: ItemClickListener<Int>) {
        itemClickListener = callback
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modal_sort, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListView()
    }

    private fun initListView() {

        arrayAdapter =
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_activated_1,
                SORT_MODAL_ARRAY
            )
        list_view.adapter = arrayAdapter
        val adapterView =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                itemClickListener?.onClick(position)
            }

        list_view.onItemClickListener = adapterView
    }


    override fun onDestroyView() {
        list_view.adapter = null
        super.onDestroyView()
    }


}
