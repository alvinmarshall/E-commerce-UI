package com.cheise_proj.e_commerce.ui.category.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.ui.category.BaseFragment
import com.cheise_proj.e_commerce.ui.category.CategoryViewModel
import com.cheise_proj.e_commerce.ui.category.adapter.CategorySlideAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_categories.*

/**
 * A simple [Fragment] subclass.
 */
class CategoriesFragment : BaseFragment<CategoryViewModel>() {
    private lateinit var pagerAdapter: CategorySlideAdapter
    private val tabTitles = arrayListOf("Women", "Men", "Kids")
    private var tabLayoutMediator: TabLayoutMediator? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }
    override fun getViewModel(): Class<CategoryViewModel> = CategoryViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pagerAdapter =
            CategorySlideAdapter(
                childFragmentManager,
                lifecycle
            )
        pagerAdapter.apply {
            addFragment(CategoryFragment.newInstance())
            addFragment(Category2Fragment.newInstance())
            addFragment(Category3Fragment.newInstance())
        }
        initViewPager()
    }

    private fun initViewPager() {
        view_pager.apply {
            this.adapter = pagerAdapter
        }
        tabLayoutMediator = TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = tabTitles[position]
            view_pager.setCurrentItem(tab.position, true)
        }
        tabLayoutMediator?.attach()

    }

    override fun onDestroyView() {
        tabLayoutMediator?.detach()
        tab_layout.removeAllViews()
        view_pager.adapter = null
        view_pager.removeAllViews()
        super.onDestroyView()
    }



}
