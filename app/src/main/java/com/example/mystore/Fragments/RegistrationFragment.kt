package com.example.mystore.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mystore.R
import com.example.mystore.Base.BaseFragment
import com.example.mystore.ViewModels.AuthViewModel
import com.example.mystore.databinding.FragmentRegistrationBinding
import java.util.stream.Stream


class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {
    val viewModelAuth: AuthViewModel by viewModels()

    override fun setup() {

    }

    override fun setClicks() {
        binding.HesabaDaxilKecid.setOnClickListener {
            findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())
        }
        binding.registerOlButton.setOnClickListener {
            subscribe()
            melumatOtur()
        }
    }

    override fun subscribe() {
        with(viewModelAuth) {
            success.observe(viewLifecycleOwner) {
                if (it) {
                    Toast.makeText(context, "Created", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.loginFragment)
                }
            }
            errorMessage.observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun melumatOtur() {
        with(binding) {
            val ad = daxilAdReg.text.toString()
            val email = daxilEmailReg.text.toString()
            val password = daxilPasswordReg.text.toString()
            val checkbox22 = checkBox2.isChecked
            viewModelAuth.register(email, password, ad, checkbox22)

        }
    }
}