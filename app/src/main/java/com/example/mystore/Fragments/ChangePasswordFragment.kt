package com.example.mystore.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mystore.ViewModels.ChangePassViewModel
import com.example.mystore.Base.BaseFragment
import com.example.mystore.databinding.FragmentChangepasswordBinding

class ChangePasswordFragment :
    BaseFragment<FragmentChangepasswordBinding>(FragmentChangepasswordBinding::inflate) {
    val viewModelProfil: ChangePassViewModel by viewModels()
    private lateinit var sp: SharedPreferences


    override fun setup() {
        sp = requireContext().getSharedPreferences("APP_DB", Context.MODE_PRIVATE)

    }

    override fun setClicks() {
        val token = sp.getString("token", "").orEmpty()
        getData(token)
        binding.changePasswordButton.setOnClickListener {
            setData(token)
        }
        binding.backstackButtonChangePass.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun subscribe() {
        with(binding) {
            with(viewModelProfil) {
                data.observe(viewLifecycleOwner) { data ->
                    data?.let {

                        emailChange.setText(it.email)
                        passwordChange.setText(it.password)
                    }
                }

                success.observe(viewLifecycleOwner) {
                    if (it) {
                        Toast.makeText(context, "Success Updated", Toast.LENGTH_LONG).show()
                    }
                }
                loading.observe(viewLifecycleOwner) {
                    if (it) {
                        progressBarChangePass.visibility = View.VISIBLE
                    } else {
                        progressBarChangePass.visibility = View.GONE
                        constraintEmailAndPass.visibility = View.VISIBLE
                    }
                }
                errorMessage.observe(viewLifecycleOwner) {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    private fun getData(token: String) {
        viewModelProfil.getData(token)
    }


    private fun setData(token: String) {
        with(binding) {
            val email = emailChange.text.toString()
            val password = passwordChange.text.toString()
            viewModelProfil.updateData(token, email, password)

        }


    }

}