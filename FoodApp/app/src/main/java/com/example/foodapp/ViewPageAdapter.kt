package com.example.foodapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.foodapp.fragments.BrowseFragment
import com.example.foodapp.fragments.FoodFragment
import com.example.foodapp.fragments.ResturantsFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FoodFragment()
            1 -> ResturantsFragment()
            2 -> BrowseFragment()
            else -> FoodFragment()
        }
    }
}