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
import com.cheise_proj.e_commerce.data.service.CheckoutService
import com.cheise_proj.e_commerce.ui.bag.adapter.CheckoutAdapter
import com.cheise_proj.e_commerce.ui.bag.adapter.CheckoutAdapter.Companion.PAYMENT_VIEW
import com.cheise_proj.e_commerce.ui.bag.adapter.CheckoutAdapter.Companion.SHIPPING_VIEW
import com.cheise_proj.e_commerce.utils.CheckoutOption
import com.cheise_proj.e_commerce.utils.DELAY_MILL
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.fragment_checkout.*
import kotlinx.android.synthetic.main.toolbar_common.*
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class CheckoutFragment : BaseFragment<CheckoutViewModel>() {

    private val args: CheckoutFragmentArgs by navArgs()
    private lateinit var adapter: CheckoutAdapter

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
        val action = CheckoutFragmentDirections.actionCheckoutFragmentToSuccessFragment()
        findNavController().navigate(action)
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
        handler.postDelayed({ subscribeObserver() }, DELAY_MILL)
    }

    private fun subscribeObserver() {
        viewModel.defaultAddress.observe(viewLifecycleOwner, Observer { data -> loadAddress(data) })
        viewModel.defaultCard.observe(viewLifecycleOwner, Observer { data -> loadCard(data) })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { status -> showProgress(status) })

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
        adapter.notifyItemChanged(PAYMENT_VIEW)
    }

    private fun loadAddress(data: AddressEntity?) {
        Timber.i("data: $data")
        adapter.addShippingAddress(data)
        adapter.notifyItemChanged(SHIPPING_VIEW)
    }

    private fun initRecyclerView() {
        adapter = CheckoutAdapter()
        adapter.submitList(CheckoutService.getCheckoutTitles())
        adapter.apply {
            addDeliveryMethod()
            setItemClickCallback(object : ItemClickListener<CheckoutOption> {
                override fun onClick(data: CheckoutOption) {
                    when (data) {
                        CheckoutOption.ADDRESS_CHANGE -> {
                            navigateToAddressPage()
                        }
                        CheckoutOption.PAYMENT_CHANGE -> {
                            navigateToPaymentCard()
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
        tv_item_1.text = args.cartTotal
    }

    override fun onDestroyView() {
        recycler_view.adapter = null
        super.onDestroyView()
    }


}
