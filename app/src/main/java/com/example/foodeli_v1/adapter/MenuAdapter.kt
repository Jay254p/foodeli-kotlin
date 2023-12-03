package com.example.foodeli_v1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodeli_v1.databinding.MenuItemBinding


class MenuAdapter(private val menuItemsName:MutableList<String>,private val menuPrice:MutableList<String>,private val menuImages:MutableList<Int>): RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun getItemCount(): Int = menuItemsName.size

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }
    inner class MenuViewHolder(private val binding: MenuItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            binding.apply {
                foodNameMenu.text=menuItemsName[position]
                priceMenu.text=menuPrice[position]
                menuImage.setImageResource(menuImages[position])
            }
        }



    }

}
