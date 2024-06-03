package com.example.mystore.Fragments

import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mystore.Api.RetrofitUtil
import com.example.mystore.Base.BaseFragment
import com.example.mystore.databinding.FragmentDetailsBinding
import com.example.mystore.data.SingleProduct.ProductDetailsDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {
    private val args by navArgs<DetailsFragmentArgs>()
    private val service = RetrofitUtil.getService()
    override fun setup() {
        binding.constraintDetails.visibility = View.GONE
        binding.progressBarDetails.visibility = View.VISIBLE
        service.getProductDetails(args.productID).enqueue(object : Callback<ProductDetailsDTO> {
            override fun onResponse(
                call: Call<ProductDetailsDTO>,
                response: Response<ProductDetailsDTO>
            ) {
                binding.progressBarDetails.visibility = View.GONE
                binding.constraintDetails.visibility = View.VISIBLE
                if (response.isSuccessful) {
                    response.body()?.let {
                        setProducts(it)
                    }
                } else {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()

                }
            }

            override fun onFailure(call: Call<ProductDetailsDTO>, t: Throwable) {
                Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun setClicks() {
        binding.backstackButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun subscribe() {

    }

    private fun setProducts(data: ProductDetailsDTO) {
        with(binding) {
            Glide.with(requireActivity()).load(data.images?.first()).into(imageDetails)
            titleDetails.text = data.title
            descriptionDetails.text = data.description
            categoryDetails.text = data.category
            priceDetails.text = "${data.price.toString()}$"
            ratingDetails.text = data.rating.toString()
        }
    }

}

