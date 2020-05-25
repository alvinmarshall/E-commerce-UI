package com.cheise_proj.e_commerce.ui.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.cheise_proj.e_commerce.BaseFragment
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.model.Reviews
import com.cheise_proj.e_commerce.ui.modal.ModalRatingFragment
import com.cheise_proj.e_commerce.ui.review.adapter.ReviewAdapter
import kotlinx.android.synthetic.main.fragment_rating.*
import kotlinx.android.synthetic.main.toolbar_common.*

/**
 * A simple [Fragment] subclass.
 */
class RatingFragment : BaseFragment<ReviewViewModel>() {
    private val args: RatingFragmentArgs by navArgs()
    private lateinit var adapter: ReviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rating, container, false)
    }

    override fun getToolBar(): Toolbar? = toolbar
    override fun getViewModel(): Class<ReviewViewModel> = ReviewViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_add_review.setOnClickListener { openReviewModal() }
        initRecyclerView()
        configViewModel()
    }


    private fun openReviewModal() {
        val modal = ModalRatingFragment.newInstance()
        modal.show(childFragmentManager, ModalRatingFragment.MODAL_RATING_TAG)
    }

    private fun initRecyclerView() {
        adapter = ReviewAdapter()
        recycler_view.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun configViewModel() {
        viewModel.getAllReviews()
        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.getReviews(args.productId ?: "0")
            .observe(viewLifecycleOwner, Observer { data -> loadReviews(data) })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { status -> showProgress(status) })
    }

    private fun showProgress(status: Boolean?) {
        status?.let { loading ->
            if (!loading) {
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun loadReviews(data: List<Reviews>?) {
        val best = data?.firstOrNull()?.reviewRating?.bestRating ?: 0
        val rating = data?.firstOrNull()?.reviewRating?.ratingValue ?: 0
        val worst = data?.firstOrNull()?.reviewRating?.worstRating ?: 0
        val overall: Float = ((best + rating + worst) / 2).toFloat()

        tv_item_1.text = overall.toString()
        tv_item_3.text = getString(R.string.reviews_placeholder, data?.size ?: 0)
        tv_item_2.text =
            getString(R.string.ratings_placeholder, data?.firstOrNull()?.reviewRating?.bestRating)
        adapter.submitList(data)
        recycler_view.adapter = adapter
    }

}
