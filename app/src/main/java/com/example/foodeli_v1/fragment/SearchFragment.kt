package com.example.foodeli_v1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodeli_v1.R
import com.example.foodeli_v1.adapter.MenuAdapter
import com.example.foodeli_v1.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: MenuAdapter

    private val originalMenuFoodName =
        listOf("Burger", "Sandwich", "Dumplings", "Salad", "Chips", "Ice Cream", "Soup")
    private val originalMenuPrice =
        listOf("Ksh 500", "Ksh 200", "Ksh 650", "Ksh 500", "Ksh 150", "Ksh 230", "Ksh 450")
    private val originalMenuFoodImages = listOf(
        R.drawable.menu1,
        R.drawable.menu2,
        R.drawable.menu3,
        R.drawable.menu4,
        R.drawable.menu5,
        R.drawable.menu6,
        R.drawable.menu7
    )

    private val filteredMenuFoodName = mutableListOf<String>()
    private val filteredMenuItemPrice = mutableListOf<String>()
    private val filteredMenuImage = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MenuAdapter(filteredMenuFoodName, filteredMenuItemPrice, filteredMenuImage)
        binding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecyclerView.adapter = adapter

        setUpSearchView()
        showAllMenu()
    }

    private fun showAllMenu() {
        filteredMenuFoodName.clear()
        filteredMenuItemPrice.clear()
        filteredMenuImage.clear()
        filteredMenuFoodName.addAll(originalMenuFoodName)
        filteredMenuItemPrice.addAll(originalMenuPrice)
        filteredMenuImage.addAll(originalMenuFoodImages)
        adapter.notifyDataSetChanged()
    }

    private fun setUpSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuItems(query.trim())
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterMenuItems(newText.trim())
                return true
            }
        })
    }

    private fun filterMenuItems(query: String) {
        filteredMenuFoodName.clear()
        filteredMenuItemPrice.clear()
        filteredMenuImage.clear()

        originalMenuFoodName.forEachIndexed { index, foodName ->
            if (foodName.contains(query, ignoreCase = true)) {
                filteredMenuFoodName.add(foodName)
                filteredMenuItemPrice.add(originalMenuPrice[index])
                filteredMenuImage.add(originalMenuFoodImages[index])
            }
        }
        adapter.notifyDataSetChanged()
    }


    companion object {

    }
}
