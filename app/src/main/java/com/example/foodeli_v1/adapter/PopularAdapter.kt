package com.example.foodeli_v1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodeli_v1.databinding.PopularItemBinding

class PopularAdapter (private val items:List<String>, private val image:List<Int>, private val price:List<String>) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder(PopularItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
    return items.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val item = items[position]
        val images = image[position]
        val price = price[position]
        holder.bind(item, price ,images)
    }

    class PopularViewHolder (private val binding: PopularItemBinding) : RecyclerView.ViewHolder(binding.root){
        private val imageView = binding.imageView8
        fun bind(item: String, price: String, imageResource: Int) {
            binding.foodNamePopular.text =item
            binding.pricePopular.text = price
            imageView.setImageResource(imageResource)

        }

    }

}