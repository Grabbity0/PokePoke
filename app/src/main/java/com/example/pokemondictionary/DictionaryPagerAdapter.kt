package com.example.pokemondictionary

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class DictionaryPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle
    ) : FragmentStateAdapter(fragmentManager, lifecycle) {


    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> DictionaryMainPageFragment()
            1 -> DictionarySubPageFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }


}
