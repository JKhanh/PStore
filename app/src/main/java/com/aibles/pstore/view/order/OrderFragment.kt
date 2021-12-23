package com.aibles.pstore.view.order

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.aibles.pstore.R
import com.aibles.pstore.databinding.FragmentOrderBinding
import com.aibles.pstore.utils.hideKeyboard
import com.aibles.pstore.view.BaseFragment
import com.aibles.pstore.view.cart.CartViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import fragmentViewBinding

@AndroidEntryPoint
class OrderFragment: BaseFragment() {
    override val layoutResId: Int
        get() = R.layout.fragment_order
    override val binding: FragmentOrderBinding by fragmentViewBinding(FragmentOrderBinding::bind)
    private val cartViewModel by activityViewModels<CartViewModel>()
    private val viewModel by viewModels<OrderViewModel>()

    private val controller = OrderEpoxyController()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvItemCart.adapter = controller.adapter

        binding.buttonConfirm.setOnClickListener {
            requireActivity().hideKeyboard()
            if(binding.textName.editText?.text.isNullOrBlank() ||
                binding.textAddress.editText?.text.isNullOrBlank() ||
                binding.textPhone.editText?.text.isNullOrBlank()){
                Toast.makeText(requireContext(), "Please fill all the information", Toast.LENGTH_SHORT).show()
            } else {
                val dialog = MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Place order Successful")
                    .setMessage("Your order will be packed and shipped within a week. Thank you for choosing us!")
                    .setPositiveButton("OK") { _: DialogInterface, _: Int ->
                        cartViewModel.deleteAfterOrder()
                        findNavController().navigateUp()
                    }
                    .setCancelable(false)
                    .create()

                dialog.show()
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
    }

    override fun onResume() {
        super.onResume()
        controller.setData(cartViewModel.orderItems)
    }
}