package com.example.foodeli_v1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodeli_v1.databinding.CartItemBinding

class CartAdapter (private val cartItems:MutableList<String>, private val cartItemPrice:MutableList<String>, private val cartImage:MutableList<Int>  ) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val itemQuantity = IntArray(cartItems.size) { 1 }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)


    }

    override fun getItemCount() = cartItems.size


    inner class CartViewHolder(private val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantity[position]
                nameCart.text = cartItems[position]
                priceCart.text = cartItemPrice[position]
                imageView7.setImageResource(cartImage[position])
                numberCart.text = quantity.toString()


                minusCart.setOnClickListener {
                    decreaseQuantity(position)
                }

                addCart.setOnClickListener {
                    increaseQuantity(position)
                }

                deleteCart.setOnClickListener {
                    val itemPosition = adapterPosition
                        if(itemPosition != RecyclerView.NO_POSITION){
                            deleteItem(itemPosition)
                        }
                }
            }
        }

        private fun decreaseQuantity(position: Int) {
            if (itemQuantity[position] >= 1) {
                itemQuantity[position]--
                binding.numberCart.text = itemQuantity[position].toString()

            }

        }

        private fun increaseQuantity(position: Int) {
            if (itemQuantity[position] <= 10) {
                itemQuantity[position]++
                binding.numberCart.text = itemQuantity[position].toString()

                }
            }
        }
        private fun deleteItem(position: Int) {
            cartItems.removeAt(position)
            cartImage.removeAt(position)
            cartItemPrice.removeAt(position)
            notifyItemRemoved(position)
            notifyItemChanged(position, cartItems.size)
            }
        }



