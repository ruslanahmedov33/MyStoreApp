package com.example.mystore.Fragments

import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.mystore.Base.BaseFragment
import com.example.mystore.databinding.FragmentOTPBinding

class OTPFragment : BaseFragment<FragmentOTPBinding>(FragmentOTPBinding::inflate) {
    override fun setup() {
    }

    override fun setClicks() {
        binding.verifyButton.setOnClickListener {
            findNavController().navigate(OTPFragmentDirections.actionOTPFragmentToAnaSehifeFragment())
        }

        binding.birOTP.addTextChangedListener {
            if(it.toString().trim().length == 1 ){
                binding.ikiOTP.requestFocus()
            }

        }

        binding.backStackButtonOTP.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun subscribe() {

    }


}