package com.aibles.pstore.view.cart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aibles.pstore.R
import com.aibles.pstore.databinding.FragmentCartBinding
import com.aibles.pstore.model.entities.dto.ItemCartLocal
import com.aibles.pstore.utils.remote.Resource
import com.aibles.pstore.utils.toTextPrice
import com.aibles.pstore.view.BaseFragment
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.AndroidEntryPoint
import fragmentViewBinding
import timber.log.Timber

@AndroidEntryPoint
class CartFragment: BaseFragment() {
    override val layoutResId: Int
        get() = R.layout.fragment_cart
    override val binding: FragmentCartBinding by fragmentViewBinding(FragmentCartBinding::bind)
    private val viewModel by activityViewModels<CartViewModel>()

    override fun onResume() {
        super.onResume()
        viewModel.getCart()
        viewModel.orderItems.clear()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCart()
        val controller = CartEpoxyController(object : CartItemListener {
            override fun onItemCheckedChange(itemCartLocal: ItemCartLocal, isCheck: Boolean) {
                viewModel.addToOrder(itemCartLocal, isCheck)
                updateTotalPrice()
            }

            override fun increaseQuantity(itemCartLocal: ItemCartLocal) {
                viewModel.updateQuantity(itemCartLocal , 1)
            }

            override fun decreaseQuantity(itemCartLocal: ItemCartLocal) {
                viewModel.updateQuantity(itemCartLocal , -1)
            }
        })
        binding.rvItemCart.adapter = controller.adapter

        viewModel.cart.observe(viewLifecycleOwner){
            it?.let{ result ->
                when(result.status){
                    Resource.Status.SUCCESS ->{
                        binding.progressCircular.visibility = View.GONE
                        if(result.data != null)
                            controller.setData(result.data)
                    }
                    Resource.Status.LOADING -> {
                        if(result.data == null)
                            binding.progressCircular.visibility = View.VISIBLE
                        else {
                            binding.progressCircular.visibility = View.GONE
                            controller.setData(result.data)
                        }
                    }
                }
            }
        }

        binding.buttonConfirmCart.setOnClickListener {
            if(viewModel.orderItems.isNotEmpty()){
                findNavController().navigate(CartFragmentDirections.actionCartFragmentToOrderFragment())
            }
        }
    }

    private fun updateTotalPrice() {
        val totalPrice = viewModel.orderItems.sumOf { it.productPrice*it.quantity }

        binding.textTotal.text = totalPrice.toTextPrice()
    }
}