package com.example.foodeli_v1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodeli_v1.R
import com.example.foodeli_v1.adapter.CartAdapter
import com.example.foodeli_v1.databinding.FragmentCartBinding

class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)

        val cartFoodName = listOf("Burger", "Sandwich", "Dumplings", "Item", "Burger", "Sandwich", "Dumplings", "Item","Burger", "Sandwich", "Dumplings", "Item", "Burger", "Sandwich", "Dumplings", "Item")
        val cartPrice = listOf("Ksh 500", "Ksh 200", "Ksh 650", "Ksh 500", "Ksh 200", "Ksh 650", "Ksh 500", "Ksh 200", "Ksh 650", "Ksh 500", "Ksh 200", "Ksh 650")
        val cartFoodImages = listOf(R.drawable.menu1, R.drawable.menu2, R.drawable.menu3, R.drawable.menu4, R.drawable.menu1, R.drawable.menu2, R.drawable.menu3, R.drawable.menu4, R.drawable.menu1, R.drawable.menu2, R.drawable.menu3, R.drawable.menu4, R.drawable.menu1, R.drawable.menu2, R.drawable.menu3, R.drawable.menu4)

        val adapter = CartAdapter(ArrayList(cartFoodName),ArrayList(cartPrice),ArrayList(cartFoodImages))
        binding.cartrecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartrecyclerView.adapter = adapter

        return binding.root
    }
}
