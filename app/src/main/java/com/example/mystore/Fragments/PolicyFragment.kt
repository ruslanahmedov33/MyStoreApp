package com.example.mystore.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mystore.Base.BaseFragment
import com.example.mystore.R
import com.example.mystore.databinding.FragmentPolicyBinding

class PolicyFragment : BaseFragment<FragmentPolicyBinding>(FragmentPolicyBinding::inflate) {
    override fun setup() {

    }

    override fun setClicks() {
        binding.backstackButtonn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun subscribe() {

    }

}