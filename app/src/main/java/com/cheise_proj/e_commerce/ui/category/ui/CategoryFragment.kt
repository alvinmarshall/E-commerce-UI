package com.cheise_proj.e_commerce.ui.category.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.model.Category
import com.cheise_proj.e_commerce.ui.category.BaseFragment
import com.cheise_proj.e_commerce.ui.category.CategoryViewModel
import com.cheise_proj.e_commerce.ui.category.adapter.CategoryImageAdapter
import com.cheise_proj.e_commerce.utils.DELAY_MILL
import kotlinx.android.synthetic.main.categories_common.*

class CategoryFragment : BaseFragment<CategoryViewModel>() {

    companion object {
        fun newInstance() =
            CategoryFragment()
    }

    private lateinit var adapter: CategoryImageAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun getViewModel(): Class<CategoryViewModel> = CategoryViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CategoryImageAdapter()
        ll_btn_frame.setOnClickListener { navigateToCategoryInfoPage() }
        configViewModel()
    }

    override fun getHeaderTextView(): TextView? {
        return tv_item_1
    }

    override fun getSubHeaderTextView(): TextView? {
        return tv_item_2
    }

    private fun configViewModel() {
        viewModel.loadCategories()
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

    private fun navigateToCategoryInfoPage() {
        val action = CategoriesFragmentDirections.actionCategoriesFragmentToCategoryInfoFragment2()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        recycler_view.adapter = null
        super.onDestroyView()
    }

}
