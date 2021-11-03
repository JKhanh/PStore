package com.aibles.pstore.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.aibles.pstore.R
import com.aibles.pstore.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import fragmentViewBinding
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment: BaseFragment() {
    override val layoutResId: Int
        get() = R.layout.fragment_home
    override val binding: FragmentHomeBinding by fragmentViewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated: HomeFragment open")
    }
}