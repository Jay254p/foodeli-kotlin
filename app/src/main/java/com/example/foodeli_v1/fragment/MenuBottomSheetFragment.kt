package com.example.foodeli_v1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodeli_v1.R
import com.example.foodeli_v1.adapter.MenuAdapter
import com.example.foodeli_v1.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentMenuBottomSheetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false)

        binding.backMenu.setOnClickListener{
            dismiss()
        }
        val menuFoodName = listOf("Burger", "Sandwich", "Dumplings","Salad" ,"Chips","Ice Cream","Soup")
        val menuPrice = listOf("Ksh 500", "Ksh 200", "Ksh 650","Ksh 500","Ksh 150","Ksh 230","Ksh 450")
        val menuFoodImages = listOf(R.drawable.menu1, R.drawable.menu2, R.drawable.menu3, R.drawable.menu4, R.drawable.menu5, R.drawable.menu6, R.drawable.menu7)

        val adapter = MenuAdapter(ArrayList(menuFoodName),ArrayList(menuPrice),ArrayList(menuFoodImages))
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter

        return binding.root
    }

    companion object {

    }
}