package com.aibles.pstore.view.account

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.aibles.pstore.R
import com.aibles.pstore.databinding.FragmentAccountBinding
import com.aibles.pstore.view.BaseFragment
import com.aibles.pstore.view.authentication.LoginActivity
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.AndroidEntryPoint
import fragmentViewBinding
import timber.log.Timber

@AndroidEntryPoint
class AccountFragment: BaseFragment() {
    override val layoutResId: Int
        get() = R.layout.fragment_account
    override val binding: FragmentAccountBinding by fragmentViewBinding(FragmentAccountBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLogin.setOnClickListener {
            if (Hawk.contains("token")) {
                Hawk.delete("token")
                binding.buttonLogin.text = "Login"
            } else {
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(Hawk.contains("token")){
            binding.buttonLogin.text = "Logout"
        }
        else {
            binding.buttonLogin.text = "Login"
        }
    }
}