package com.cheise_proj.e_commerce.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.adapter.CategoryImageAdapter
import com.cheise_proj.e_commerce.model.Category
import com.cheise_proj.e_commerce.utils.DELAY_MILL
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.fragment_product2.*
import kotlinx.android.synthetic.main.toolbar_collapse_with_button.*
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class Product2Fragment : BaseFragment<ProductViewModel>() {
    private val args: Product2FragmentArgs by navArgs()
    private lateinit var adapter: CategoryImageAdapter

    override fun getToolBar(): Toolbar? = toolbar
    override fun getImageUrl(): String? = args.pCategoryImage
    override fun getToolbarImage(): ImageView? = app_bar_image
    override fun getToolBarText(): String? = "New Collection"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product2, container, false)
    }

    override fun getViewModel(): Class<ProductViewModel>  = ProductViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        configViewModel()
    }

    private fun configViewModel() {
        viewModel.getCategories()
        handler.postDelayed({ subscribeObserver() }, DELAY_MILL)
    }

    private fun subscribeObserver() {
        viewModel.productCategory.observe(viewLifecycleOwner, Observer(this::loadCategories))
        viewModel.isLoading.observe(viewLifecycleOwner, Observer(this::showProgress))
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

    private fun initRecyclerView() {

        adapter = CategoryImageAdapter().apply {
            setItemCallback(object : ItemClickListener<String?> {
                override fun onClick(data: String?) {
                    toast("category id: $data")
                }
            })
        }

        recycler_view.apply {
            hasFixedSize()
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    override fun onDestroyView() {
        recycler_view.adapter = null
        super.onDestroyView()
    }

}
