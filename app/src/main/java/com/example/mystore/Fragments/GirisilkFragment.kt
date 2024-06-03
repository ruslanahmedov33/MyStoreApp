package com.example.mystore.Fragments

import android.content.Context
import android.content.SharedPreferences
import androidx.navigation.fragment.findNavController
import com.example.mystore.Base.BaseFragment
import com.example.mystore.databinding.FragmentGirisilkBinding

class GirisilkFragment : BaseFragment<FragmentGirisilkBinding>(FragmentGirisilkBinding::inflate) {

    private lateinit var sp: SharedPreferences

    override fun setup() {
        sp = requireContext().getSharedPreferences("APP_DB", Context.MODE_PRIVATE)
        val token = sp.getString("token", "")

        if (!token.isNullOrEmpty()) {
            findNavController().navigate(GirisilkFragmentDirections.actionGirisilkFragmentToAnaSehifeFragment())
        }

    }

    override fun setClicks() {
        binding.registerFragmentDaxilButton.setOnClickListener {
            findNavController().navigate(GirisilkFragmentDirections.actionGirisilkFragmentToRegistrationFragment())
        }
        binding.daxilOlLogin1Button.setOnClickListener {
            findNavController().navigate(GirisilkFragmentDirections.actionGirisilkFragmentToLoginFragment())
        }
    }

    override fun subscribe() {

    }
}