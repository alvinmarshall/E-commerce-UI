package com.cheise_proj.e_commerce.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cheise_proj.e_commerce.BaseFragment
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.data.db.entity.CardEntity
import com.cheise_proj.e_commerce.di.module.glide.GlideApp
import com.cheise_proj.e_commerce.model.ProfileSection
import com.cheise_proj.e_commerce.ui.profile.adapter.ProfileAdapter
import com.cheise_proj.e_commerce.ui.profile.adapter.ProfileAdapter.Companion.ADDRESS_VIEW
import com.cheise_proj.e_commerce.ui.profile.adapter.ProfileAdapter.Companion.ORDERS_VIEW
import com.cheise_proj.e_commerce.ui.profile.adapter.ProfileAdapter.Companion.PAYMENT_VIEW
import com.cheise_proj.e_commerce.utils.ItemClickListener
import com.cheise_proj.e_commerce.utils.USER_AVATAR_URL
import com.cheise_proj.e_commerce.utils.USER_EMAIL
import com.cheise_proj.e_commerce.utils.USER_NAME
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.profile_header.*
import kotlinx.android.synthetic.main.toolbar_common.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : BaseFragment<ProfileViewModel>() {
    private lateinit var adapter: ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun getToolBar(): Toolbar? = toolbar

    override fun getViewModel(): Class<ProfileViewModel> = ProfileViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        configViewModel()
        initProfileInfo()
    }

    private fun initProfileInfo() {
        // test data
        tv_header.text = USER_NAME
        tv_sub_header.text = USER_EMAIL
        GlideApp.with(requireContext()).load(USER_AVATAR_URL).circleCrop().into(img_item)
    }

    private fun initRecyclerView() {
        adapter = ProfileAdapter()
        adapter.apply {
            setItemClickCallback(object :
                ItemClickListener<ProfileAdapter.Companion.ProfileOption> {
                override fun onClick(data: ProfileAdapter.Companion.ProfileOption) {
                    when (data) {
                        ProfileAdapter.Companion.ProfileOption.ORDER_DETAILS -> {
                            navigateToOrder()
                        }
                        ProfileAdapter.Companion.ProfileOption.ADDRESS -> {
                            navigateToAddress()
                        }
                        ProfileAdapter.Companion.ProfileOption.PAYMENT_METHOD -> {
                            navigateToPayment()
                        }
                    }
                }
            })
        }
        recycler_view.apply {
            hasFixedSize()
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun navigateToOrder() {
        val action = ProfileFragmentDirections.actionProfileFragmentToOrdersFragment()
        findNavController().navigate(action)
    }

    private fun navigateToAddress() {
        val action = ProfileFragmentDirections.actionProfileFragmentToAddressFragment()
        findNavController().navigate(action)
    }

    private fun navigateToPayment() {
        val action = ProfileFragmentDirections.actionProfileFragmentToPaymentCardFragment()
        findNavController().navigate(action)
    }

    private fun configViewModel() {
        viewModel.getProfileTitles()
        viewModel.getShoppingAddress()
        viewModel.getOrders()
        viewModel.getDefaultPayment()
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.profileTitles.observe(viewLifecycleOwner, Observer { data -> loadTitles(data) })
        viewModel.shippingAddressCount.observe(
            viewLifecycleOwner,
            Observer { data -> loadAddressCount(data) })
        viewModel.ordersCount.observe(viewLifecycleOwner, Observer { data -> loadOrderCount(data) })
        viewModel.paymentMethod.observe(viewLifecycleOwner, Observer { data -> loadPayment(data) })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { status -> showProgress(status) })
    }

    private fun loadPayment(data: CardEntity?) {
        adapter.addPaymentMethod(data)
        adapter.notifyItemChanged(PAYMENT_VIEW)
    }

    private fun loadOrderCount(data: Int?) {
        adapter.addOrderCount(data)
        adapter.notifyItemChanged(ORDERS_VIEW)
    }

    private fun loadAddressCount(data: Int) {
        adapter.addShippingAddressCount(data)
        adapter.notifyItemChanged(ADDRESS_VIEW)
    }

    private fun showProgress(status: Boolean?) {
        status?.let { loading ->
            if (!loading) {
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun loadTitles(data: List<ProfileSection>?) {
        adapter.submitList(data)
        recycler_view.adapter = adapter

    }

    override fun onDestroyView() {
        recycler_view.adapter = null
        super.onDestroyView()
    }
}
