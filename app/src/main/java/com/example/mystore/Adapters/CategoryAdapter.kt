package com.example.mystore.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mystore.data.SettingsData.Product
import com.example.mystore.databinding.ProductItemCategoryBinding

class CategoryAdapter :RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){

    private val items = arrayListOf<Product>()
    var onClickItemListener : (Product)->Unit={}


    inner class CategoryViewHolder(private val binding: ProductItemCategoryBinding):
            RecyclerView.ViewHolder(binding.root){
                fun find(item:Product){
                    with(binding){
                        cardViewCategory.setOnClickListener {
                            onClickItemListener(item)
                        }

                        priceCategory.text = "${item.price}$"
                        titleCategory.text = item.title
                        ratingTextCategory.text=item.rating.toString()
                        Glide.with(imageCategory.context).load(item.images?.first()).into(imageCategory)
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(ProductItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
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