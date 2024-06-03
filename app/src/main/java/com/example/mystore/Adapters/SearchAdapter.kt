package com.example.mystore.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mystore.data.SettingsData.Product
import com.example.mystore.databinding.ProductItemSearchBinding

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchVH>(){

    private val items= arrayListOf<Product>()
    var onClickItemListener : (Product)->Unit={}

    inner class SearchVH(private val binding:ProductItemSearchBinding) : RecyclerView.ViewHolder(binding.root){
        fun find(item:Product){
            binding.cardViewSearch.setOnClickListener {
                onClickItemListener(item)
            }
            with(binding){
                titleSearch.text=item.title
                priceSearch.text="${item.price}$"
                ratingTextSearch.text=item.rating.toString()
                Glide.with(imageSearch.context).load(item.images?.first()).into(imageSearch)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchVH {
        return SearchVH(ProductItemSearchBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SearchVH, position: Int) {
       holder.find(items[position])
    }


    fun updateList(newList: List<Product>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
}