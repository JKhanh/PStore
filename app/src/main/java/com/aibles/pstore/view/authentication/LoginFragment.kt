package com.aibles.pstore.view.authentication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.aibles.pstore.R
import com.aibles.pstore.databinding.FragmentLoginBinding
import com.aibles.pstore.model.entities.Account
import com.aibles.pstore.utils.hideKeyboard
import com.aibles.pstore.view.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import fragmentViewBinding

@AndroidEntryPoint
class LoginFragment: BaseFragment() {
    override val layoutResId: Int
        get() = R.layout.fragment_login
    override val binding: FragmentLoginBinding by fragmentViewBinding(FragmentLoginBinding::bind)
    private val viewModel by activityViewModels<AuthenticationViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        binding.buttonLogin.setOnClickListener {
            val username = binding.edittextUsername.editText?.text
            val password = binding.edittextPassword.editText?.text
            if(username.isNullOrBlank()) {
                binding.edittextUsername.error = getString(R.string.missing_email)
            } else if(password.isNullOrBlank()) {
                binding.edittextPassword.error = getString(R.string.missing_password)
            } else {
                val account = Account(
                    username.toString(), password.toString()
                )
                viewModel.login(account)
                requireActivity().hideKeyboard()
            }
        }

        viewModel.loginStatus.observe(viewLifecycleOwner){
            if(it.isSuccessful()){
                Toast.makeText(requireContext(), getString(R.string.login_success), Toast.LENGTH_SHORT).show()
                requireActivity().finish()
            }
        }

        binding.buttonRegister.setOnClickListener {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            )
        }
    }
}