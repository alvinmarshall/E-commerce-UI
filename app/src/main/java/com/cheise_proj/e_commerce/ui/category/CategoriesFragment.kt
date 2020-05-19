package com.cheise_proj.e_commerce.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.ui.category.adapter.CategorySlideAdapter
import com.cheise_proj.e_commerce.ui.category.ui.Category2Fragment
import com.cheise_proj.e_commerce.ui.category.ui.Category3Fragment
import com.cheise_proj.e_commerce.ui.category.ui.CategoryFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_categories.*

/**
 * A simple [Fragment] subclass.
 */
class CategoriesFragment : BaseFragment() {
    private lateinit var pagerAdapter: CategorySlideAdapter
    private val tabTitles = arrayListOf("Women", "Men", "Kids")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

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
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = tabTitles[position]
            view_pager.setCurrentItem(tab.position, true)
        }.attach()
    }


}
