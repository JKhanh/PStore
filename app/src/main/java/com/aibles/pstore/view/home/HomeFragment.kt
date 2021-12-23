package com.aibles.pstore.view.home

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.helper.widget.Carousel
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import coil.size.Precision
import coil.size.Scale
import com.aibles.pstore.R
import com.aibles.pstore.databinding.FragmentHomeBinding
import com.aibles.pstore.model.entities.Product
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
    private val viewModel by activityViewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initCarousel()
        val productEpoxyController = ProductEpoxyController(object : ProductOnClick {
            override fun onClick(product: Product){
                viewModel.secProduct.push(product)
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProductDetailFragment())
            }
        })
        productEpoxyController.spanCount = 2
        binding.rvProduct.adapter = productEpoxyController.adapter
        binding.rvProduct.layoutManager = GridLayoutManager(requireContext(), 2).apply {
            spanSizeLookup = productEpoxyController.spanSizeLookup
        }

        viewModel.products.observe(viewLifecycleOwner){
            it?.let { result ->
                when(result.status){
                    Resource.Status.SUCCESS ->{
                        productEpoxyController.isLoading = false
                        productEpoxyController.setData(result.data)
                    }
                    Resource.Status.LOADING -> {
                        productEpoxyController.isLoading = true
                    }
                }
            }
        }
    }

//    private fun initCarousel() {
//        binding.carousel.carousel.setAdapter(object : Carousel.Adapter {
//            override fun count(): Int = 3
//
//            override fun populate(view: View?, index: Int) {
//            }
//
//            override fun onNewItem(index: Int) {
//            }
//
//        })
//        binding.carousel.apply {
//            view1.load(getString(R.string.image1)) {
//                crossfade(true)
//                precision(Precision.EXACT)
//                scale(Scale.FILL)
//            }
//            view2.load(getString(R.string.image2)) {
//                crossfade(true)
//                precision(Precision.EXACT)
//                scale(Scale.FILL)
//            }
//            view3.load(getString(R.string.image3)) {
//                crossfade(true)
//                precision(Precision.EXACT)
//                scale(Scale.FILL)
//            }
//        }
//    }

    override fun onResume() {
        super.onResume()
        viewModel.getProducts()
    }
}