package com.cheise_proj.e_commerce.ui.category.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.model.Category
import com.cheise_proj.e_commerce.ui.category.BaseFragment
import com.cheise_proj.e_commerce.utils.DELAY_MILL
import kotlinx.android.synthetic.main.categories_common.*

/**
 * A simple [Fragment] subclass.
 */
class Category3Fragment : BaseFragment() {
    companion object {
        fun newInstance() =
            Category3Fragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configViewModel()
    }

    override fun getSubHeaderText(): String? = "Up to 45% off"

    override fun getHeaderTextView(): TextView? {
        return tv_item_1
    }

    override fun getSubHeaderTextView(): TextView? {
        return tv_item_2
    }

    private fun configViewModel() {
        initRecyclerView()
        handler.postDelayed({ subscribeObserver() }, DELAY_MILL)
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    private fun subscribeObserver() {
        viewModel.categories.observe(viewLifecycleOwner, Observer(this::loadCategories))
    }

    private fun loadCategories(data: List<Category>?) {
        adapter.submitList(data)
        recycler_view.adapter = adapter
    }

    override fun onDestroyView() {
        recycler_view.adapter = null
        super.onDestroyView()
    }


}
