package com.aibles.pstore.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.aibles.pstore.R
import com.aibles.pstore.databinding.FragmentAccountBinding
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
        Timber.d("onViewCreated: AccountFragment open")
    }
}