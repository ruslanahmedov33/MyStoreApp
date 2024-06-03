package com.example.mystore.Fragments

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mystore.R
import com.example.mystore.Base.BaseFragment
import com.example.mystore.ViewModels.AuthViewModel
import com.example.mystore.databinding.FragmentParolUnutdumBinding


class ParolUnutdumFragment :
    BaseFragment<FragmentParolUnutdumBinding>(FragmentParolUnutdumBinding::inflate) {
    val viewModelAuth: AuthViewModel by viewModels()
    private lateinit var viewModel: AuthViewModel
    override fun setup() {
        viewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        setClicks()
        subscribe()
    }

    override fun setClicks() {
        binding.ParolBerpaEtButton.setOnClickListener {
            parolBerpaEt()
        }

        binding.backStackButtonForgot.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun subscribe() {
        with(viewModelAuth) {
            success.observe(viewLifecycleOwner) {
                if (it) {
                    Toast.makeText(context, "Check Your Email", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.loginFragment)
                }
            }
            errorMessage.observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun parolBerpaEt() {
        val email = binding.daxilEmailBerpa.text.toString()

        if (email.isEmpty()) {
            Toast.makeText(context, "Fields are empty", Toast.LENGTH_LONG).show()
            return
        }
        viewModelAuth.resetPassword(email)
    }

}



