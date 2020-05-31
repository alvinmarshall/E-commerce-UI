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
import com.cheise_proj.e_commerce.data.db.entity.AddressEntity
import com.cheise_proj.e_commerce.ui.bag.adapter.AddressAdapter
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.fragment_address.*
import kotlinx.android.synthetic.main.toolbar_common.*

/**
 * A simple [Fragment] subclass.
 */
class AddressFragment : BaseFragment<CheckoutViewModel>() {
    private lateinit var adapter: AddressAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_address, container, false)
    }

    override fun getToolBar(): Toolbar? = toolbar
    override fun getViewModel(): Class<CheckoutViewModel> = CheckoutViewModel::class.java
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab_add.setOnClickListener { navigateToCreateAddressPage() }
        initRecyclerView()
        configViewModel()
    }

    private fun navigateToCreateAddressPage() {
        val action = AddressFragmentDirections.actionAddressFragmentToCreateAddressFragment()
        findNavController().navigate(action)
    }

    private fun initRecyclerView() {
        adapter = AddressAdapter()
        adapter.apply {
            setItemClickCallback(object :ItemClickListener<AddressEntity>{
                override fun onClick(data: AddressEntity) {
                    viewModel.updateAddress(data)

                }
            })
        }
        recycler_view.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
        }

    }

    private fun configViewModel() {
        viewModel.getAddresses()
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.addresses.observe(viewLifecycleOwner, Observer { data -> loadAddresses(data) })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { status -> showProgress(status) })
    }

    private fun showProgress(status: Boolean?) {
        status?.let { loading ->
            if (!loading) {
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun loadAddresses(data: List<AddressEntity>?) {
        if (data.isNullOrEmpty()) {
            showNoData(root)
            return
        }
        adapter.submitList(data)
        recycler_view.adapter = adapter
    }

    override fun onDestroyView() {
        recycler_view.adapter = null
        super.onDestroyView()
    }

}
