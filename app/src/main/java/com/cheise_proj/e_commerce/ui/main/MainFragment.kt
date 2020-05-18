package com.cheise_proj.e_commerce.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.adapter.VerticalProductAdapter
import com.cheise_proj.e_commerce.model.Category
import com.cheise_proj.e_commerce.ui.BaseFragment
import com.cheise_proj.e_commerce.utils.ClickOption
import com.cheise_proj.e_commerce.utils.DELAY_MILL
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.toolbar_collapse_with_button.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : BaseFragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: VerticalProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun getToolBar(): Toolbar? = toolbar
    override fun showButtonOnToolbar(): Boolean = true
    override fun getToolbarImage(): ImageView? = app_bar_image
    override fun getImageUrl(): String? = "https://picsum.photos/400/700/?blur=2"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configViewModel()
    }

    private fun configViewModel() {
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        initRecyclerView()
        viewModel.getProductCategories()
        handler.postDelayed({subscribeObserver()}, DELAY_MILL)
    }

    private fun subscribeObserver() {
        viewModel.productCategory.observe(viewLifecycleOwner, Observer(this::loadProductCategory))
    }

    private fun loadProductCategory(data: List<Category>?) {
        adapter.submitList(data)
        recycler_view.adapter = adapter

    }

    private fun initRecyclerView() {
        adapter = VerticalProductAdapter().apply {
            setItemClickCallback(object : ItemClickListener<Pair<Category?, ClickOption>> {
                override fun onClick(data: Pair<Category?, ClickOption>) {
                    when (data.second) {
                        ClickOption.VIEW -> {
                        }
                        ClickOption.VIEW_ALL -> navigateToMain2Page(data.first?.imageUrl)
                    }
                }
            })
        }
        recycler_view.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }


    private fun navigateToMain2Page(data: String?) {
        val action = MainFragmentDirections.actionMainFragmentToMain2Fragment(data)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        recycler_view.adapter = null
        super.onDestroyView()
    }


}
