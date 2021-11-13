package com.aibles.pstore.view.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.aibles.pstore.R
import com.aibles.pstore.databinding.FragmentHomeBinding
import com.aibles.pstore.utils.remote.Resource
import com.aibles.pstore.view.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import fragmentViewBinding
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment: BaseFragment() {
    override val layoutResId: Int
        get() = R.layout.fragment_home
    override val binding: FragmentHomeBinding by fragmentViewBinding(FragmentHomeBinding::bind)
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productEpoxyController = ProductEpoxyController()
        binding.rvProduct.adapter = productEpoxyController.adapter

        viewModel.products.observe(viewLifecycleOwner){
            it?.let { result ->
                when(result.status){
                    Resource.Status.SUCCESS ->{
                        binding.progressCircular.visibility = View.GONE
                        productEpoxyController.setData(result.data!!.results)
                    }
                    Resource.Status.LOADING -> {
                        binding.progressCircular.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}