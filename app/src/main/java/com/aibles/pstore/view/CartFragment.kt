package com.aibles.pstore.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.aibles.pstore.R
import com.aibles.pstore.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint
import fragmentViewBinding
import timber.log.Timber

@AndroidEntryPoint
class CartFragment: BaseFragment() {
    override val layoutResId: Int
        get() = R.layout.fragment_cart
    override val binding: FragmentCartBinding by fragmentViewBinding(FragmentCartBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated: CartFragment open")
    }
}