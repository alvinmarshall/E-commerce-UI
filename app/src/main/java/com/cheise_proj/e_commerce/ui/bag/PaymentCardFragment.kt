package com.cheise_proj.e_commerce.ui.bag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cheise_proj.e_commerce.BaseFragment
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.data.db.entity.CardEntity
import com.cheise_proj.e_commerce.ui.bag.adapter.CardAdapter
import com.cheise_proj.e_commerce.ui.modal.ModalCreateCardFragment
import com.cheise_proj.e_commerce.utils.ItemClickListener
import kotlinx.android.synthetic.main.fragment_payment_card.*
import kotlinx.android.synthetic.main.toolbar_common.*
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class PaymentCardFragment : BaseFragment<CheckoutViewModel>() {
    private lateinit var adapter: CardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_card, container, false)
    }

    override fun getToolBar(): Toolbar? = toolbar
    override fun getViewModel(): Class<CheckoutViewModel> = CheckoutViewModel::class.java
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab_add.setOnClickListener { openCreateCardModal() }
        adapter = CardAdapter()
        adapter.apply {
            setItemClickCallback(object : ItemClickListener<CardEntity> {
                override fun onClick(data: CardEntity) {
                    viewModel.updatePaymentCard(data)
                }
            })
        }
        initRecyclerView()
        configViewModel()
    }

    private fun openCreateCardModal() {
        val modal = ModalCreateCardFragment.newInstance()
        modal.itemCallback(object : ItemClickListener<CardEntity> {
            override fun onClick(data: CardEntity) {
                Timber.i("card: $data")
                viewModel.addPaymentCard(data)
            }
        })
        modal.show(childFragmentManager, ModalCreateCardFragment.MODAL_CREATE_CARD_TAG)
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
        }

    }

    private fun configViewModel() {
        viewModel.getPaymentCards()
        subscribeObserver()

    }

    private fun subscribeObserver() {
        viewModel.cards.observe(viewLifecycleOwner, Observer { data -> loadCards(data) })
    }

    private fun loadCards(data: List<CardEntity>?) {
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
