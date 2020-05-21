package com.cheise_proj.e_commerce.ui.category.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.cheise_proj.e_commerce.R
import com.cheise_proj.e_commerce.data.service.FilterService
import kotlinx.android.synthetic.main.fragment_brand.*
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class BrandFragment : Fragment() {
    private lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_brand, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_multiple_choice,
            FilterService.getBrandOpts()
        )
        val listener = AdapterView.OnItemClickListener { _, _, position, _ ->
            toast("position: $position")
        }
        list_view.onItemClickListener = listener
        list_view.adapter = arrayAdapter
    }

    override fun onDestroyView() {
        list_view.adapter = null
        super.onDestroyView()
    }

}
