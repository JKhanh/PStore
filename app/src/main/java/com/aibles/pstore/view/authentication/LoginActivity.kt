package com.aibles.pstore.view.authentication

import activityViewBinding
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aibles.pstore.R
import com.aibles.pstore.databinding.ActivityLoginBinding
import com.aibles.pstore.model.entities.Account
import com.aibles.pstore.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: AppCompatActivity() {
    private val binding: ActivityLoginBinding by activityViewBinding(ActivityLoginBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}