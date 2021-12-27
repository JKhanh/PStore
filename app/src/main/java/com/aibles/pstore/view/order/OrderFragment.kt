package com.aibles.pstore.view.order

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aibles.pstore.R
import com.aibles.pstore.databinding.FragmentOrderBinding
import com.aibles.pstore.utils.hideKeyboard
import com.aibles.pstore.utils.remote.Resource
import com.aibles.pstore.view.BaseFragment
import com.aibles.pstore.view.cart.CartViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import fragmentViewBinding

@AndroidEntryPoint
class OrderFragment: BaseFragment() {
    override val layoutResId: Int
        get() = R.layout.fragment_order
    override val binding: FragmentOrderBinding by fragmentViewBinding(FragmentOrderBinding::bind)
    private val cartViewModel by activityViewModels<CartViewModel>()
    private val viewModel by activityViewModels<OrderViewModel>()

    private val controller = OrderEpoxyController()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvItemCart.adapter = controller.adapter

        binding.buttonConfirm.setOnClickListener {
            requireActivity().hideKeyboard()
            if(binding.textName.editText?.text.isNullOrBlank() ||
                binding.textAddress.editText?.text.isNullOrBlank() ||
                binding.textPhone.editText?.text.isNullOrBlank()){
                Toast.makeText(requireContext(), getString(R.string.missing_info), Toast.LENGTH_SHORT).show()
            } else {
                viewModel.createOrder(cartViewModel.orderItems)
                cartViewModel.deleteAfterOrder()
                finishOrder()
            }
        }

        viewModel.userProfile.observe(viewLifecycleOwner){
            if(it.isSuccessful()){
                it.data?.let { profile ->
                    binding.apply {
                        textName.editText?.setText("${profile.firstName} ${profile.lastName}")
                        textPhone.editText?.setText(profile.phoneNumber)
                        textAddress.editText?.setText(profile.address)
                    }
                }
            }
        }

//        observeOrder()
    }

    private fun observeOrder() {
        viewModel.order.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE

                }
            }
        }
    }

    private fun finishOrder(){
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.order_success))
            .setMessage(getString(R.string.order_date))
            .setPositiveButton("OK") { _: DialogInterface, _: Int ->
                findNavController().navigateUp()
            }
            .setCancelable(false)
            .create()

        dialog.show()
    }

    override fun onResume() {
        super.onResume()
        controller.setData(cartViewModel.orderItems)
    }
}