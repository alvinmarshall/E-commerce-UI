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
import com.cheise_proj.e_commerce.ui.category.CategoryViewModel
import com.cheise_proj.e_commerce.ui.category.adapter.CategoryImageAdapter
import com.cheise_proj.e_commerce.utils.DELAY_MILL
import kotlinx.android.synthetic.main.categories_common.*

/**
 * A simple [Fragment] subclass.
 */
class Category3Fragment : BaseFragment<CategoryViewModel>() {
    companion object {
        fun newInstance() =
            Category3Fragment()
    }

    private lateinit var adapter: CategoryImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category3, container, false)
    }

    override fun getViewModel(): Class<CategoryViewModel> = CategoryViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CategoryImageAdapter()

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
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { status -> showProgress(status) })
    }

    private fun showProgress(status: Boolean?) {
        status?.let { loading ->
            if (!loading) {
                progressBar.visibility = View.GONE
            }
        }
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
