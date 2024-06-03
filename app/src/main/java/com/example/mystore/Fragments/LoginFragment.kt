package com.example.mystore.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mystore.Base.BaseFragment
import com.example.mystore.ViewModels.AuthViewModel
import com.example.mystore.databinding.FragmentLoginBinding


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    val viewModelAuth: AuthViewModel by viewModels()
    private lateinit var viewModel: AuthViewModel
    private lateinit var sp: SharedPreferences

    override fun setup() {
        viewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
    }

    override fun subscribe() {
        with(viewModelAuth) {
            success.observe(viewLifecycleOwner) {
                if (it) {

                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToOTPFragment())
                }
            }
            errorMessage.observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun setClicks() {
        sp = requireContext().getSharedPreferences("APP_DB", Context.MODE_PRIVATE)

        binding.ParoluYaddanCixartdim.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToParolUnutdumFragment())
        }
        binding.HesabYaratKecid.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
        }
        binding.enterBtn.setOnClickListener {
            daxilOl()
        }
    }


    fun daxilOl() {

        with(binding) {
            val email = daxilEmailLog.text.toString()
            val password = daxilPassLog.text.toString()
            val checkbox = checkBoxRemember.isChecked
            val sp = requireContext().getSharedPreferences("APP_DB", Context.MODE_PRIVATE)

            viewModelAuth.login(email, password, checkbox, sp)

        }
    }
}


