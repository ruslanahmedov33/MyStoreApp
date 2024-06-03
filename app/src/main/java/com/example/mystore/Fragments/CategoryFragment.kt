package com.example.mystore.Fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mystore.Adapters.CategoryAdapter
import com.example.mystore.Base.BaseFragment
import com.example.mystore.ViewModels.CategoryViewModel
import com.example.mystore.databinding.FragmentCategoryBinding

class CategoryFragment : BaseFragment<FragmentCategoryBinding>(FragmentCategoryBinding::inflate) {
    val viewModel: CategoryViewModel by viewModels()
    val adapter = CategoryAdapter()
    override fun setup() {
        val category: CategoryFragmentArgs by navArgs()
        viewModel.getItemByCategory(category.category ?: "")
        layoutmanager()
        binding.recyclerVIewCategory.adapter = adapter
    }


    override fun setClicks() {
        binding.backStackButtonCategory.setOnClickListener {
            findNavController().popBackStack()
        }
        adapter.onClickItemListener = {
            findNavController().navigate(
                CategoryFragmentDirections.actionCategoryFragmentToDetailsFragment(
                    it.id ?: 0
                )
            )
        }

    }

    override fun subscribe() {
        with(viewModel) {
            success.observe(viewLifecycleOwner) {
                adapter.updateList(it.products ?: emptyList())
            }
            loading.observe(viewLifecycleOwner){
                if (it) {
                    binding.progressBarCategory.visibility = View.VISIBLE
                } else {
                    binding.progressBarCategory.visibility = View.GONE
                }
            }
        }


    }

    private fun layoutmanager() {
        with(binding) {
            recyclerVIewCategory.adapter = adapter
            recyclerVIewCategory.layoutManager = GridLayoutManager(context, 2)
        }
    }
}