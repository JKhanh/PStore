package com.aibles.pstore.view.authentication

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.aibles.pstore.R
import com.aibles.pstore.databinding.FragmentRegisterBinding
import com.aibles.pstore.model.entities.dto.AccountRegister
import com.aibles.pstore.model.entities.dto.Profile
import com.aibles.pstore.view.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import fragmentViewBinding
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class RegisterFragment: BaseFragment() {
    override val layoutResId: Int
        get() = R.layout.fragment_register
    override val binding: FragmentRegisterBinding by fragmentViewBinding(FragmentRegisterBinding::bind)
    private val viewModel by activityViewModels<AuthenticationViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        binding.edittextBirthday.setEndIconOnClickListener {
            val dateDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    binding.edittextBirthday.editText?.setText(
                        "$year-${month+1}-$day")
                }, 1990, 0, 1)
            dateDialog.setOnShowListener{
                dateDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.black))
                dateDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.black))
            }

            dateDialog.show()
        }

        binding.buttonRegister.setOnClickListener {
            var check1 = true
            var check2 = true
            var check3 = true
            var check4 = true
            var check5 = true
            var check6 = true
            if(viewModel.username.value.isNullOrEmpty()){
                binding.edittextUsername.error = "Email cannot be empty"
                check1 = false
            }
            if(viewModel.password.value.isNullOrEmpty()){
                binding.edittextPassword.error = "Password cannot be empty"
                check2 = false
            }
            if(binding.edittextPassword2.editText?.text.isNullOrEmpty()){
                binding.edittextPassword2.error = "Password cannot be empty"
                check3 = false
            } else {
                if(viewModel.password.value!!.equals(binding.edittextPassword2.editText?.text)){
                    binding.edittextPassword2.error = "Password not matched"
                    check3 = false
                }
            }
            if(binding.edittextFirstname.editText?.text.isNullOrEmpty()){
                binding.edittextFirstname.error = "Firstname cannot be empty"
                check4 = false
            }
            if(binding.edittextLastname.editText?.text.isNullOrEmpty()){
                binding.edittextLastname.error = "Lastname cannot be empty"
                check5 = false
            }
            if(binding.edittextPhone.editText?.text.isNullOrEmpty()){
                binding.edittextPhone.error = "Phone number cannot be empty"
                check6 = false
            }

            if(check1 && check2 && check3 && check4 && check5 && check6){
                val profile = Profile(
                    firstName = binding.edittextFirstname.editText!!.text.toString(),
                    lastName = binding.edittextLastname.editText!!.text.toString(),
                    phoneNumber = binding.edittextPhone.editText!!.text.toString(),
                    birthDate = binding.edittextBirthday.editText?.text.toString(),
                    gender = "M"
                )
                val accountRegister = AccountRegister(
                    email = binding.edittextUsername.editText!!.text.toString(),
                    password = binding.edittextPassword.editText!!.text.toString(),
                    profile = profile
                )
                viewModel.signup(accountRegister)
            }
        }

        viewModel.registerStatus.observe(viewLifecycleOwner){
            if(it.isSuccessful()){
                Toast.makeText(requireContext(), "Register Successful!", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        }
    }
}