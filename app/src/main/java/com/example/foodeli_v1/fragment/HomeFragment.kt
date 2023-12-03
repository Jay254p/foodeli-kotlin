package com.example.foodeli_v1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.foodeli_v1.R
import com.example.foodeli_v1.adapter.PopularAdapter
import com.example.foodeli_v1.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.viewMenu.setOnClickListener {
            val bottomSheetDialog = MenuBottomSheetFragment()
            bottomSheetDialog.show(parentFragmentManager, "Test")
        }
        return binding.root






    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.banner1, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner2, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner3, ScaleTypes.FIT))

        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setImageList(imageList, ScaleTypes.FIT)
        imageSlider.setItemClickListener(object :ItemClickListener{
            override fun doubleClick(position: Int) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(position: Int) {
                val itemPosition = imageList[position]
                val itemMessasage = "Selected Image $position"
                Toast.makeText(requireContext(), itemMessasage, Toast.LENGTH_SHORT).show()
            }
        })


            val foodName = listOf("Burger", "Sandwich", "Dumplings", "Sandwich", "Dumplings", "Item", "Item")
            val price = listOf("Ksh 500", "Ksh 200", "Ksh 650", "Ksh 500", "Ksh 200", "Ksh 650", "KSh 500")
            val popularFoodImages = listOf(R.drawable.menu1, R.drawable.menu2, R.drawable.menu3, R.drawable.menu4, R.drawable.menu2, R.drawable.menu3, R.drawable.menu4)

            val adapter = PopularAdapter(foodName, popularFoodImages, price)
            binding.recyclerViewPopular.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerViewPopular.adapter = adapter
        }


    companion object {

    }
}