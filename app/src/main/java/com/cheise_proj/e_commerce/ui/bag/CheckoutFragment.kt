package com.cheise_proj.e_commerce.ui.bag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.cheise_proj.e_commerce.BaseFragment
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.data.db.entity.AddressEntity
import com.cheise_proj.e_commerce.data.db.entity.CardEntity
import com.cheise_proj.e_commerce.data.db.entity.DeliveryEntity
import com.cheise_proj.e_commerce.data.db.entity.OrderEntity
import com.cheise_proj.e_commerce.data.service.CheckoutService
import com.cheise_proj.e_commerce.data.service.DeliveryService
import com.cheise_proj.e_commerce.ui.bag.adapter.CheckoutAdapter
import com.cheise_proj.e_commerce.ui.bag.adapter.CheckoutAdapter.Companion.DELIVERY_VIEW
import com.cheise_proj.e_commerce.ui.bag.adapter.CheckoutAdapter.Companion.PAYMENT_VIEW
import com.cheise_proj.e_commerce.ui.bag.adapter.CheckoutAdapter.Companion.SHIPPING_VIEW
import com.cheise_proj.e_commerce.utils.CheckoutOption
import com.cheise_proj.e_commerce.utils.DELAY_MILL
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.fragment_checkout.*
import kotlinx.android.synthetic.main.toolbar_common.*
import timber.log.Timber
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class CheckoutFragment : BaseFragment<CheckoutViewModel>() {

    private val args: CheckoutFragmentArgs by navArgs()
    private lateinit var adapter: CheckoutAdapter
    private var checkoutTotalCost: Float = 0f
    private var addressId: Int = 0
    private var cardId: Int = 0
    private var currentDelivery: DeliveryEntity? = null
    private var deliverCost = 0f
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkout, container, false)
    }

    override fun getToolBar(): Toolbar? = toolbar
    override fun getViewModel(): Class<CheckoutViewModel> = CheckoutViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_submit.setOnClickListener { navigateToSuccessPage() }
        initRecyclerView()
        initTotalViews()
        configViewModel()
    }

    private fun navigateToSuccessPage() {
        val isCheckoutValid: Boolean = validateCheckout()
        if (!isCheckoutValid) return
        val order =
            OrderEntity(
                id = 0,
                total = checkoutTotalCost.toString(),
                quantity = args.cartExtra.quantity,
                addressId = addressId,
                cardId = cardId,
                delivery = currentDelivery,
                status = DeliveryService.DELIVERED,
                date = Date().time.toString(),
                tracker = DeliveryService.getDeliveryTracker(),
                promoCode = args.cartExtra.promoCode
            )
        viewModel.addOrder(order)
        snackMessage(root, getString(R.string.cart_item_add_msg))

        val action = CheckoutFragmentDirections.actionCheckoutFragmentToSuccessFragment()
        findNavController().navigate(action)
    }

    private fun validateCheckout(): Boolean {
        if (checkoutTotalCost == 0f) {
            snackMessage(root, getString(R.string.cart_empty_msg))
            return false
        }
        if (deliverCost == 0f) {
            snackMessage(root, getString(R.string.cart_no_delivery_type_msg))
            return false
        }
        if (addressId == 0) {
            snackMessage(root, getString(R.string.cart_no_address_msg))
            return false
        }
        if (cardId == 0) {
            snackMessage(root, getString(R.string.cart_no_payment_method_msg))
            return false
        }
        return true
    }

    private fun navigateToPaymentCard() {
        val action = CheckoutFragmentDirections.actionCheckoutFragmentToPaymentCardFragment()
        findNavController().navigate(action)

    }

    private fun navigateToAddressPage() {
        val action = CheckoutFragmentDirections.actionCheckoutFragmentToAddressFragment()
        findNavController().navigate(action)

    }

    private fun configViewModel() {
        viewModel.getDefaultAddress()
        viewModel.getDefaultCard()
        viewModel.getDeliveryTypes()
        handler.postDelayed({ subscribeObserver() }, DELAY_MILL)
    }

    private fun subscribeObserver() {
        viewModel.defaultAddress.observe(viewLifecycleOwner, Observer { data -> loadAddress(data) })
        viewModel.defaultCard.observe(viewLifecycleOwner, Observer { data -> loadCard(data) })
        viewModel.deliveryType.observe(
            viewLifecycleOwner,
            Observer { data -> loadDeliveryTypes(data) })
        viewModel.delivery.observe(viewLifecycleOwner, Observer { data -> loadDelivery(data) })
        viewModel.deliveryCost.observe(
            viewLifecycleOwner,
            Observer { data -> getDeliveryCost(data) })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { status -> showProgress(status) })

    }

    private fun getDeliveryCost(data: Int?) {
        Timber.i("deliveryCost: $data")
        deliverCost = data?.toFloat() ?: 0f
        initTotalViews()
    }

    private fun loadDelivery(data: DeliveryEntity?) {
        Timber.i("delivery: $data")
        currentDelivery = data
    }

    private fun loadDeliveryTypes(data: List<DeliveryEntity>) {
        adapter.addDeliveryMethod(data)
        adapter.notifyItemChanged(DELIVERY_VIEW)
    }

    private fun showProgress(status: Boolean?) {
        status?.let { loading ->
            if (!loading) {
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun loadCard(data: CardEntity?) {
        Timber.i("data: $data")
        adapter.addPaymentMethod(data)
        cardId = data?.id ?: 0
        adapter.notifyItemChanged(PAYMENT_VIEW)
    }

    private fun loadAddress(data: AddressEntity?) {
        Timber.i("data: $data")
        adapter.addShippingAddress(data)
        addressId = data?.id ?: 0
        adapter.notifyItemChanged(SHIPPING_VIEW)
    }

    private fun initRecyclerView() {
        adapter = CheckoutAdapter()
        adapter.submitList(CheckoutService.getCheckoutTitles())
        adapter.apply {
            setItemClickCallback(object : ItemClickListener<Pair<CheckoutOption, Int?>> {
                override fun onClick(data: Pair<CheckoutOption, Int?>) {
                    when (data.first) {
                        CheckoutOption.ADDRESS_CHANGE -> {
                            navigateToAddressPage()
                        }
                        CheckoutOption.PAYMENT_CHANGE -> {
                            navigateToPaymentCard()
                        }
                        CheckoutOption.DELIVERY -> {
                            viewModel.getDelivery(data.second)
                        }
                    }
                }
            })
        }
        recycler_view.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
        }
        recycler_view.adapter = adapter
    }

    private fun initTotalViews() {
        val cartTotalAmount = args.cartExtra.totalAmount ?: 0f
        checkoutTotalCost = cartTotalAmount + deliverCost
        applyCostTotal(cartTotalAmount, deliverCost, checkoutTotalCost)
    }

    private fun applyCostTotal(
        cartCost: Float,
        deliverCost: Float,
        checkoutCost: Float
    ) {
        if (cartCost > 0) {
            tv_item_1.text = getString(R.string.money_end_placeholder, cartCost.toString())
            tv_item_2.text =
                getString(R.string.money_end_placeholder, deliverCost.toString())
            tv_item_3.text = getString(R.string.money_end_placeholder, checkoutCost.toString())
        } else {
            tv_item_1.text = getString(R.string.money_end_placeholder, "0")
            tv_item_2.text =
                getString(R.string.money_end_placeholder, "0")
            tv_item_3.text = getString(R.string.money_end_placeholder, "0")
        }
    }

    override fun onDestroyView() {
        recycler_view.adapter = null
        super.onDestroyView()
    }


}
