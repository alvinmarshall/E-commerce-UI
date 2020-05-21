package com.cheise_proj.e_commerce.ui.category.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.model.Category
import com.cheise_proj.e_commerce.ui.category.BaseFragment
import com.cheise_proj.e_commerce.ui.category.CategoryViewModel
import com.cheise_proj.e_commerce.ui.category.adapter.CategoryAdapter
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.fragment_category_info.*
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class CategoryInfoFragment : BaseFragment<CategoryViewModel>() {
    companion object {
        fun newInstance() =
            CategoryInfoFragment()
    }

    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_info, container, false)
    }

    override fun getViewModel(): Class<CategoryViewModel> = CategoryViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        configViewModel()
    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this, factory)[CategoryViewModel::class.java]
        viewModel.loadCategories()
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.categories.observe(viewLifecycleOwner, Observer { data -> loadCategories(data) })
    }

    private fun loadCategories(data: List<Category>?) {
        categoryAdapter.submitList(data)
        recycler_view.adapter = categoryAdapter
    }

    private fun initRecyclerView() {
        categoryAdapter = CategoryAdapter()
        categoryAdapter.apply {
            setItemClickCallback(object : ItemClickListener<Category?> {
                override fun onClick(data: Category?) {
                    toast("name: ${data?.categoryName}")
                    navigateToCatalog(data)
                }
            })
        }
        recycler_view.apply {
            hasFixedSize()
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun navigateToCatalog(data: Category?) {
        if (data == null) return
        val categoryOpts = arrayOf(data.categoryID, data.categoryName)
        val action =
            CategoryInfoFragmentDirections.actionCategoryInfoFragmentToCatalogFragment(categoryOpts)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        recycler_view.adapter = null
        super.onDestroyView()
    }

}
