package com.aibles.pstore.view.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.aibles.pstore.R
import com.aibles.pstore.databinding.FragmentProductDetailBinding
import com.aibles.pstore.model.entities.Product
import com.aibles.pstore.utils.remote.Resource
import com.aibles.pstore.view.BaseFragment
import com.aibles.pstore.view.cart.CartViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import fragmentViewBinding
import timber.log.Timber

@AndroidEntryPoint
class ProductDetailFragment: BaseFragment() {
    override val layoutResId: Int
        get() = R.layout.fragment_product_detail
    override val binding: FragmentProductDetailBinding by fragmentViewBinding(FragmentProductDetailBinding::bind)
    private val viewModel by activityViewModels<HomeViewModel>()
    private val cartViewModel by activityViewModels<CartViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.product = viewModel.secProduct.peek()
        viewModel.getProductRecommend()

        val productRecommend = RecommendEpoxyController(object : ProductOnClick {
            override fun onClick(product: Product) {
                viewModel.secProduct.push(product)
                findNavController().navigate(ProductDetailFragmentDirections.actionProductDetailFragmentSelf())
            }
        })
        binding.rvProductRec.adapter = productRecommend.adapter

        viewModel.productRec.observe(viewLifecycleOwner){
            it?.let { result ->
                when(result.status){
                    Resource.Status.SUCCESS ->{
                        productRecommend.setData(result.data!!)
                    }
                }
            }
        }

        binding.buttonCart.setOnClickListener {
            cartViewModel.addToCart(binding.product!!)
            Snackbar.make(requireView(), "Add to cart Success!", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (!viewModel.secProduct.empty())
        viewModel.secProduct.pop()
    }

}