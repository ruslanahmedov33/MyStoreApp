
package com.example.mystore.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mystore.data.SettingsData.Product
import com.example.mystore.databinding.ProductItemBinding


class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    var onClickItemListener : (Product)->Unit={}
    private val items = arrayListOf<Product>()

    inner class ProductViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun find(item: Product) {


            with(binding) {
                cardView.setOnClickListener {
                    onClickItemListener(item)
                }


            price.text = "${item.price}$"
            title.text = item.title
            ratingText.text=item.rating.toString()
            Glide.with(imageee.context).load(item.images?.first()).into(imageee)
        }
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        return ProductViewHolder(
            ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = items.getOrNull(position)
        item?.let {
            holder.find(item)
        }

    }


    fun updateList(newList: List<Product>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }


}
