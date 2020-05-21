package com.cheise_proj.e_commerce.ui.category.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.adapter.HorizontalProductAdapter
import com.cheise_proj.e_commerce.model.Category
import com.cheise_proj.e_commerce.model.Product
import com.cheise_proj.e_commerce.ui.category.BaseFragment
import com.cheise_proj.e_commerce.ui.category.CategoryViewModel
import com.cheise_proj.e_commerce.ui.category.adapter.CatalogAdapter
import com.cheise_proj.e_commerce.ui.category.adapter.CategoryChipAdapter
import com.cheise_proj.e_commerce.ui.modal.ModalSortFragment
import com.cheise_proj.e_commerce.ui.modal.ModalSortFragment.Companion.SORT_MODAL_ARRAY
import com.cheise_proj.e_commerce.utils.DELAY_MILL
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.fragment_category_catalog.*
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class CategoryCatalogFragment : BaseFragment<CategoryViewModel>() {
    private lateinit var adapter: CatalogAdapter
    private lateinit var gridAdapter: HorizontalProductAdapter
    private lateinit var chipAdapter: CategoryChipAdapter
    private val args: CategoryCatalogFragmentArgs by navArgs()
    private var isLinearViewList = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_catalog, container, false)
    }

    override fun getViewModel(): Class<CategoryViewModel> = CategoryViewModel::class.java
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CatalogAdapter()
        gridAdapter = HorizontalProductAdapter()
        chipAdapter = CategoryChipAdapter()
        chipAdapter.apply {
            setItemClickCallback(object : ItemClickListener<String?> {
                override fun onClick(data: String?) {
                    toast("data: $data")
                }
            })
        }
        btn_view_list.setOnClickListener {
            isLinearViewList = !isLinearViewList
            viewModel.setViewStatus(isLinearViewList)
        }
        btn_filter.setOnClickListener { navigateToFilterPage() }
        tv_selected_filter.text = SORT_MODAL_ARRAY[0]
        tv_selected_filter.setOnClickListener { openSortModal() }
        initTextHeader()
        initRecyclerView()
        configViewModel()
    }

    private fun navigateToFilterPage() {
        val action =
            CategoryCatalogFragmentDirections.actionCatalogFragmentToCategoryFilterFragment()
        findNavController().navigate(action)
    }

    private fun openSortModal() {
        val sortFragment = ModalSortFragment.newInstance()
        sortFragment.setItemCallback(object : ItemClickListener<Int> {
            override fun onClick(data: Int) {
                tv_selected_filter.text = SORT_MODAL_ARRAY[data]
            }
        })
        sortFragment.show(childFragmentManager, ModalSortFragment.MODAL_SORT_TAG)
    }

    private fun initTextHeader() {
        tv_header.text = args.categoryOpt[1]
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
        }
        recycler_view_chip.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }

    }

    private fun configViewModel() {
        viewModel.loadCategories()
        viewModel.loadProducts()
        handler.postDelayed({ subscribeObserver() }, DELAY_MILL)

    }

    private fun subscribeObserver() {
        viewModel.categories.observe(viewLifecycleOwner, Observer(this::loadCategory))
        viewModel.getViewStatus.observe(viewLifecycleOwner, Observer { status ->
            viewModel.products.observe(
                viewLifecycleOwner,
                Observer { data -> loadProducts(data, status) })

        })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { status -> showProgress(status) })
    }

    private fun showProgress(status: Boolean?) {
        status?.let { loading ->
            if (!loading) {
                progressBar.visibility = View.GONE
            }
        }
    }


    private fun loadCategory(data: List<Category>?) {
        data?.let { category ->
            setCategoryChips(category)
        }
    }

    private fun setCategoryChips(category: List<Category>) {
        chipAdapter.submitList(category)
        recycler_view_chip.adapter = chipAdapter
    }

    private fun loadProducts(
        data: List<Product>?, status: Boolean
    ) {
        recycler_view.adapter = null
        if (status) {
            adapter.submitList(data)
            recycler_view.adapter = adapter
            return
        }
        gridAdapter.submitList(data)
        recycler_view.adapter = gridAdapter

    }

    override fun onDestroyView() {
        recycler_view.adapter = null
        recycler_view_chip.adapter = null
        super.onDestroyView()
    }

}
