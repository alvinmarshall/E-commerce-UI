package com.cheise_proj.e_commerce.ui.bag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cheise_proj.e_commerce.BaseFragment
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.data.db.entity.FavoriteEntity
import com.cheise_proj.e_commerce.data.db.entity.ProductWithCart
import com.cheise_proj.e_commerce.ui.bag.adapter.CartAdapter
import com.cheise_proj.e_commerce.ui.modal.ModalPromoFragment
import com.cheise_proj.e_commerce.utils.CartOptions
import com.cheise_proj.e_commerce.utils.DELAY_MILL
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.enter_promo_code.*
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.toolbar_common.*
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class CartFragment : BaseFragment<BagViewModel>() {
    private lateinit var adapter: CartAdapter
    private var totalPrice = 0f
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun getToolBar(): Toolbar? {
        toolbar.inflateMenu(R.menu.common_search_menu)
        return toolbar
    }

    override fun getViewModel(): Class<BagViewModel> = BagViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_promo_code.setOnClickListener { openPromoModal() }
        btn_checkout.setOnClickListener { navigateToCheckout() }
        initRecyclerView()
        configViewModel()
    }

    private fun navigateToCheckout() {
        val action = CartFragmentDirections.actionCartFragmentToCheckoutFragment(totalPrice.toString())
        findNavController().navigate(action)
    }

    private fun openPromoModal() {
        val modal = ModalPromoFragment.newInstance()
        modal.setClickCallback(object : ItemClickListener<String> {
            override fun onClick(data: String) {
                tv_promo_code.text = data
            }
        })

        modal.show(childFragmentManager, ModalPromoFragment.MODAL_PROMO_TAG)
    }

    private fun initRecyclerView() {
        adapter = CartAdapter()
        adapter.apply {
            setItemClickCallback(object : ItemClickListener<Pair<CartOptions, ProductWithCart?>> {
                override fun onClick(data: Pair<CartOptions, ProductWithCart?>) {
                    when (data.first) {
                        CartOptions.INCREASE -> {
                            val cart = data.second?.cartEntity!!
                            var count = cart.quantity
                            count++
                            toast("increase quantity by $count")
                            cart.quantity = count
                            viewModel.updateCart(cart)
                        }
                        CartOptions.DECREASE -> {
                            val cart = data.second?.cartEntity!!
                            var count = cart.quantity
                            if (count == 1) return
                            count--
                            toast("decrease quantity by $count")
                            cart.quantity = count
                            viewModel.updateCart(cart)
                        }
                        CartOptions.FAVORITE -> {
                            toast("added to favorite")
                            val favoriteEntity = FavoriteEntity()
                            with(data.second) {
                                favoriteEntity.productId = this?.cartEntity?.productId ?: ""
                                favoriteEntity.color = this?.cartEntity?.color ?: ""
                                favoriteEntity.size = this?.cartEntity?.size ?: ""
                            }
                            viewModel.addToFavorite(favoriteEntity)
                        }
                        CartOptions.REMOVE -> {
                            toast("removed from cart")
                            viewModel.removeFromCart(data.second?.cartEntity?.id ?: 0)
                        }
                    }
                }
            })
        }
        recycler_view.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
        }

    }

    private fun configViewModel() {
        viewModel.getProductCart()
        handler.postDelayed({ subscribeObserver() }, DELAY_MILL)

    }

    private fun subscribeObserver() {
        viewModel.productCart.observe(
            viewLifecycleOwner,
            Observer { data -> loadProductCart(data) })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { status -> showProgress(status) })
    }

    private fun showProgress(status: Boolean?) {
        status?.let { loading ->
            if (!loading) {
                progressBar.visibility = View.GONE

            }
        }
    }

    private fun loadProductCart(data: List<ProductWithCart>?) {
        setTotal(data)
        adapter.submitList(data)
        recycler_view.adapter = adapter
    }

    private fun setTotal(data: List<ProductWithCart>?) {

        data?.forEach {
            val price = it.productEntity?.unitPrice?.toFloat() ?: 0f
            val quantity = it.cartEntity?.quantity?.toFloat() ?: 1f
            totalPrice += price * quantity
        }
        tv_item_2.text = getString(R.string.money_end_placeholder, totalPrice.toString())
    }

    override fun onDestroyView() {
        recycler_view.adapter = null
        super.onDestroyView()
    }

}
