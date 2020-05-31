package com.cheise_proj.e_commerce.ui.bag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.cheise_proj.e_commerce.BaseFragment
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.data.db.entity.AddressEntity
import kotlinx.android.synthetic.main.fragment_create_address.*
import kotlinx.android.synthetic.main.toolbar_common.*

/**
 * A simple [Fragment] subclass.
 */
class CreateAddressFragment : BaseFragment<CheckoutViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_address, container, false)
    }

    override fun getToolBar(): Toolbar? = toolbar
    override fun getViewModel(): Class<CheckoutViewModel> = CheckoutViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_save_address.setOnClickListener { createAddress() }
    }

    private fun createAddress() {
        val fullName = et_name.text.toString()
        val address = et_address.text.toString()
        val city = et_city.text.toString()
        val state = et_state.text.toString()
        val zipCode = et_zip_code.text.toString()
        val country = et_country.text.toString()
        val addressEntity = AddressEntity(0, fullName, address, city, state, zipCode, country, 0)
        viewModel.addAddress(addressEntity)
        snackMessage(root, "address created")
        activity?.onBackPressed()
    }

}
