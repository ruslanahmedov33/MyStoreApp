package com.example.mystore.Fragments


import androidx.navigation.fragment.findNavController
import com.example.mystore.Base.BaseFragment
import com.example.mystore.databinding.FragmentSettingssBinding

class SettingssFragment :
    BaseFragment<FragmentSettingssBinding>(FragmentSettingssBinding::inflate) {
    override fun setup() {

    }

    override fun setClicks() {

        binding.backstackButtonn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.passwordChangeGo.setOnClickListener {
            findNavController().navigate(SettingssFragmentDirections.actionSettingssFragmentToProfilFragment())
        }


    }

    override fun subscribe() {

    }

}