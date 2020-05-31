package com.cheise_proj.e_commerce.ui.product

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.BaseFragment
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.adapter.HorizontalProductAdapter
import com.cheise_proj.e_commerce.data.db.entity.CartEntity
import com.cheise_proj.e_commerce.di.module.glide.GlideApp
import com.cheise_proj.e_commerce.model.Product
import com.cheise_proj.e_commerce.ui.modal.ModalColorFragment
import com.cheise_proj.e_commerce.ui.modal.ModalSizeFragment
import kotlinx.android.synthetic.main.fragment_product_detail.*
import kotlinx.android.synthetic.main.fragment_product_detail.tv_item_1
import kotlinx.android.synthetic.main.product_detail_base.*
import kotlinx.android.synthetic.main.toolbar_common.*

/**
 * A simple [Fragment] subclass.
 */
class ProductDetailFragment : BaseFragment<ProductViewModel>() {
    private val args: ProductDetailFragmentArgs by navArgs()
    private lateinit var adapter: HorizontalProductAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun getToolBar(): Toolbar? {
        toolbar.inflateMenu(R.menu.product_detail_menu)
        return toolbar
    }

    override fun getViewModel(): Class<ProductViewModel> = ProductViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rating = kotlin.math.floor(Math.random() * 5).toInt()
        btn_size_opt.setOnClickListener { openSizeModal() }
        btn_color_opt.setOnClickListener { openColorModal() }
        ratingBar.rating = 5f
        tv_rating_number.text = getString(R.string.bracket_text_placeholder,rating)
        tv_rating_number.setOnClickListener { navigateToReviewPage() }
        btn_cart.setOnClickListener { addToCart() }
        fab_fav.setOnClickListener { addToFavorite() }
        initRecyclerView()
        configViewModel()

    }

    private fun addToFavorite() {
        viewModel.addToFavorite(args.productId?:"")
        snackMessage(root,"item added")
    }

    private fun addToCart() {
        val cart = CartEntity()
        cart.productId = args.productId?:""
        viewModel.addToCart(cart)
        snackMessage(root,"item added")
    }

    private fun navigateToReviewPage() {
        val action =
            ProductDetailFragmentDirections.actionProductDetailFragmentToRatingFragment(args.productId)
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.product_detail_menu, menu)
    }


    private fun openColorModal() {
        val modal = ModalColorFragment.newInstance()
        modal.show(childFragmentManager, ModalColorFragment.MODAL_Color_TAG)
    }

    private fun openSizeModal() {
        val modal = ModalSizeFragment.newInstance()
        modal.show(childFragmentManager, ModalSizeFragment.MODAL_SIZE_TAG)
    }

    private fun initRecyclerView() {
        adapter = HorizontalProductAdapter()
        recycler_view.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }

    private fun configViewModel() {
        viewModel.getProduct(args.productId)
        viewModel.getMayLikeProducts()
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.product.observe(viewLifecycleOwner, Observer { data -> loadProduct(data) })
        viewModel.products.observe(viewLifecycleOwner, Observer { data -> loadProducts(data) })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { status -> showProgress(status) })

    }

    private fun showProgress(status: Boolean?) {
        status?.let { loading ->
            if (!loading) {
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun loadProducts(data: List<Product>?) {
        adapter.submitList(data)
        tv_item_number.text = "${data?.size}"
        recycler_view.adapter = adapter
    }

    private fun loadProduct(data: Product?) {
        GlideApp.with(requireContext()).load(data?.imageUrl).centerCrop().into(img_item)
        tv_item_1.text = data?.productName
        tv_item_2.text = getString(R.string.money_placeholder, data?.unitPrice)
        tv_item_3.text = data?.productName
        tv_item_4.text = data?.quantityPerUnit

    }

    override fun onDestroyView() {
        recycler_view.adapter = null
        super.onDestroyView()
    }

}
