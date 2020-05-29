package com.cheise_proj.e_commerce.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.BaseFragment
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.data.db.entity.CartEntity
import com.cheise_proj.e_commerce.data.db.entity.ProductWithFavorite
import com.cheise_proj.e_commerce.model.Category
import com.cheise_proj.e_commerce.ui.category.adapter.CategoryChipAdapter
import com.cheise_proj.e_commerce.ui.favorite.adapter.FavoriteAdapter
import com.cheise_proj.e_commerce.utils.DELAY_MILL
import com.cheise_proj.e_commerce.utils.FavoriteOption
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.toolbar_common.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : BaseFragment<FavoriteViewModel>() {
    private lateinit var adapter: FavoriteAdapter
    private lateinit var chipAdapter: CategoryChipAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun getToolBar(): Toolbar? {
        toolbar.inflateMenu(R.menu.common_search_menu)
        return toolbar
    }

    override fun getViewModel(): Class<FavoriteViewModel> = FavoriteViewModel::class.java
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FavoriteAdapter()
        adapter.apply {
            setClickItemCallback(object :
                ItemClickListener<Pair<FavoriteOption, ProductWithFavorite?>> {
                override fun onClick(data: Pair<FavoriteOption, ProductWithFavorite?>) {
                    when (data.first) {
                        FavoriteOption.CLOSE -> {
                            viewModel.removeFavorite(data.second?.favoriteEntity?.id)
                        }
                        FavoriteOption.CART -> {
                            val cart = CartEntity()
                            with(data.second) {
                                cart.color = this?.favoriteEntity?.color!!
                                cart.size = this.favoriteEntity.size
                                cart.productId = this.favoriteEntity.productId
                            }
                            viewModel.addToCart(cart)
                        }
                    }
                }
            })
        }
        chipAdapter = CategoryChipAdapter()
        initRecyclerView()
        configViewModel()
    }

    private fun configViewModel() {
        viewModel.loadCategories()
        viewModel.getFavoriteProducts()
        handler.postDelayed({ subscribeObserver() }, DELAY_MILL)
    }

    private fun subscribeObserver() {
        viewModel.favoriteProducts.observe(
            viewLifecycleOwner,
            Observer { data -> loadFavorites(data) })
        viewModel.categories.observe(viewLifecycleOwner, Observer(this::loadCategory))

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


    private fun loadFavorites(data: List<ProductWithFavorite>?) {
        if (data.isNullOrEmpty()) {
           showNoData(root)
            return
        }
        adapter.submitList(data)
        recycler_view.adapter = adapter
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

    override fun onDestroyView() {
        recycler_view_chip.adapter = null
        recycler_view.adapter = null
        super.onDestroyView()
    }

}
