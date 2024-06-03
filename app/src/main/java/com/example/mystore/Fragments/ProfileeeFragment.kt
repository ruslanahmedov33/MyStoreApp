package com.example.mystore.Fragments

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mystore.Base.BaseFragment
import com.example.mystore.ViewModels.ChangePassViewModel
import com.example.mystore.ViewModels.ProfilViewModel
import com.example.mystore.databinding.FragmentProfileeeBinding

class ProfileeeFragment :
    BaseFragment<FragmentProfileeeBinding>(FragmentProfileeeBinding::inflate) {
    val ProfilViewModel: ProfilViewModel by viewModels()
    private lateinit var sp: SharedPreferences
    override fun setup() {
        sp = requireContext().getSharedPreferences("APP_DB", Context.MODE_PRIVATE)
        val token = sp.getString("token", "").orEmpty()
        getData(token)
    }

    override fun setClicks() {
        binding.settingsProfile.setOnClickListener {
            findNavController().navigate(ProfileeeFragmentDirections.actionProfileeeFragmentToSettingssFragment())
        }
        binding.privacyPolicyButton.setOnClickListener {
            findNavController().navigate(ProfileeeFragmentDirections.actionProfileeeFragmentToPolicyFragment())
        }
        binding.logOutButton.setOnClickListener {
            spDelete()
            findNavController().navigate(ProfileeeFragmentDirections.actionProfileeeFragmentToGirisilkFragment())
        }
    }

    override fun subscribe() {
        with(binding) {
            with(ProfilViewModel) {
                data.observe(viewLifecycleOwner) { data ->
                    data?.let {
                        textName.setText(it.name)
                    }
                }
                errorMessage.observe(viewLifecycleOwner) {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

        private fun getData(token: String) {
            ProfilViewModel.getData(token)
        }

        fun spDelete() {
            sp = requireContext().getSharedPreferences("APP_DB", Context.MODE_PRIVATE)

            sp.edit().remove("token").commit()

        }


}