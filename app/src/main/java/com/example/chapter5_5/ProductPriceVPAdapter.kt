package com.example.chapter5_5

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProductPriceVPAdapter(
    childFragmentManager: FragmentManager,
    getLifecycle: Lifecycle
) :
    FragmentStateAdapter(childFragmentManager, getLifecycle) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PriceAllFragment()  // 전체
            1 -> PriceAllFragment()  // 1만원 이하
            2 -> PriceAllFragment()  // 2~4만원대
            else -> PriceAllFragment()  // 5만원 이상
        }
    }
}