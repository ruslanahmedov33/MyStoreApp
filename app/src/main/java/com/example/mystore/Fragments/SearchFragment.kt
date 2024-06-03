package com.example.mystore.Fragments


import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.mystore.data.SettingsData.ProductDTO
import com.example.mystore.Api.RetrofitUtil
import com.example.mystore.Adapters.SearchAdapter
import com.example.mystore.Base.BaseFragment
import com.example.mystore.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchFragment() : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val service = RetrofitUtil.getService()
    private val adapter = SearchAdapter()
    override fun setup() {
        binding.recyclerSearchProducts.adapter = adapter
        cagir()
    }

    override fun setClicks() {
        adapter.onClickItemListener = {
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragment2ToDetailsFragment(
                    it.id ?: 0
                )
            )
        }
    }

    override fun subscribe() {

    }

    private fun cagir() {
        setData()
        getData()
        layoutmanager()
    }

    private fun setData(){

            binding.progressbarSearch.visibility = View.VISIBLE
            service.getAllProducts().enqueue(object : Callback<ProductDTO> {
                override fun onResponse(call: Call<ProductDTO>, response: Response<ProductDTO>) {
                    if (response.isSuccessful) {
                        response.body()?.let {

                            binding.progressbarSearch.visibility = View.GONE
                            adapter.updateList(it.products ?: emptyList())
                        }
                    } else {
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()

                    }
                }

                override fun onFailure(call: Call<ProductDTO>, t: Throwable) {
                    Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })

    }

    private fun getData() {


        binding.editSearchText.addTextChangedListener {
            val query = it.toString()
            binding.progressbarSearch.visibility = View.VISIBLE
            service.searchProducts(query).enqueue(object : Callback<ProductDTO> {
                override fun onResponse(call: Call<ProductDTO>, response: Response<ProductDTO>) {
                    if (response.isSuccessful) {
                        response.body()?.let {

                            binding.progressbarSearch.visibility = View.GONE
                            adapter.updateList(it.products ?: emptyList())
                        }
                    } else {
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()

                    }
                }

                override fun onFailure(call: Call<ProductDTO>, t: Throwable) {
                    Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_LONG).show()
                }
            })
        }

    }

    private fun layoutmanager() {
        with(binding) {
            recyclerSearchProducts.adapter = adapter
        }
    }

}