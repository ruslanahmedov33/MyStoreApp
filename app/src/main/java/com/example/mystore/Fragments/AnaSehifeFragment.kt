package com.example.mystore.Fragments

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mystore.Base.BaseFragment
import com.example.mystore.Adapters.ProductAdapter
import com.example.mystore.ViewModels.AnaSehifeViewModel
import com.example.mystore.databinding.FragmentAnaSehifeBinding


class AnaSehifeFragment :
    BaseFragment<FragmentAnaSehifeBinding>(FragmentAnaSehifeBinding::inflate) {
    lateinit var viewModel: AnaSehifeViewModel
    val adapter = ProductAdapter()

    override fun setup() {
        binding.recyclerView.adapter = adapter
        viewModel = ViewModelProvider(requireActivity())[AnaSehifeViewModel::class.java]
        subscribe()
        setup2()

    }

    override fun setClicks() {
        adapter.onClickItemListener = {
            findNavController().navigate(
                AnaSehifeFragmentDirections.actionAnaSehifeFragmentToDetailsFragment(
                    it.id ?: 0
                )
            )
        }
        binding.buttonNote.setOnClickListener{
            findNavController().navigate(AnaSehifeFragmentDirections.actionAnaSehifeFragmentToCategoryFragment(
                "laptops"
            ))
        }
        binding.buttonPhone.setOnClickListener{
            findNavController().navigate(AnaSehifeFragmentDirections.actionAnaSehifeFragmentToCategoryFragment(
                "smartphones"
            ))
        }
        binding.buttonHealt.setOnClickListener{
            findNavController().navigate(AnaSehifeFragmentDirections.actionAnaSehifeFragmentToCategoryFragment(
                "skincare"
            ))
        }
        binding.buttonPerfume.setOnClickListener{
            findNavController().navigate(AnaSehifeFragmentDirections.actionAnaSehifeFragmentToCategoryFragment(
                "fragrances"
            ))
        }

    }

    override fun subscribe() {
        with(viewModel) {
            with(binding) {
                loading.observe(viewLifecycleOwner) {
                    if (it) {
                        progressBar3.visibility = View.VISIBLE
                    } else {
                        progressBar3.visibility = View.GONE
                    }
                }
                error.observe(viewLifecycleOwner) {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
                success.observe(viewLifecycleOwner) {
                    adapter.updateList(it.products ?: emptyList())
                }
            }
        }
    }


    fun setup2() {
        with(binding) {
            recyclerView.adapter = adapter
        }
    }


}