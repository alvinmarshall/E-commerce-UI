package com.cheise_proj.e_commerce.ui.category.ui

import android.os.Bundle
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

class CategoryFragment : BaseFragment() {

    companion object {
        fun newInstance() =
            CategoryFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configViewModel()
    }

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
