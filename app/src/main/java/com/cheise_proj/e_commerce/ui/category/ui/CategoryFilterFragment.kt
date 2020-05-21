package com.cheise_proj.e_commerce.ui.category.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.adapter.FilterAdapter
import com.cheise_proj.e_commerce.data.service.FilterService
import com.cheise_proj.e_commerce.model.FilterOpts
import com.cheise_proj.e_commerce.utils.*
import kotlinx.android.synthetic.main.fragment_category_filter.*

/**
 * A simple [Fragment] subclass.
 */
class CategoryFilterFragment : Fragment() {
    private lateinit var adapter: FilterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FilterAdapter()
        adapter.apply {
            addSizesOpts(FilterService.getSizesOpts())
            addCategoryOpts(FilterService.getCategoryOpts())
            addBrandOpts(FilterService.getBrandOpts())
            addColorOpts(FilterService.getColorOpts(requireContext()))
            brandClickCallback(object : ItemClickListener<String?> {
                override fun onClick(data: String?) {
                    navigateToBrandPage()
                }
            })
        }
        recycler_view.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)

        }
        val filterOpts = mutableListOf(
            FilterOpts(
                "Price Range", PRICE_VIEW
            ),
            FilterOpts(
                "Colors", COLORS_VIEW
            ),
            FilterOpts(
                "Sizes", SIZE_VIEW
            ),
            FilterOpts(
                "Category", CATEGORY_VIEW
            ),
            FilterOpts(
                "Brand", BRAND_VIEW
            )
        )
        adapter.submitList(filterOpts)
        recycler_view.adapter = adapter

    }

    private fun navigateToBrandPage() {
        val action =
            CategoryFilterFragmentDirections.actionCategoryFilterFragmentToBrandFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        recycler_view.adapter = null
        super.onDestroyView()
    }

}
