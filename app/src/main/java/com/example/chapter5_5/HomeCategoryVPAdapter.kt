package com.example.chapter5_5

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeCategoryVPAdapter(childFragmentManager: FragmentManager, getLifecycle: Lifecycle) :
    FragmentStateAdapter(childFragmentManager, getLifecycle) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BirthdayFragment()    // 생일 카테고리
            1 -> ParentsFragment()     // 부모님 카테고리
            2 -> LightGiftFragment()   // 가벼운 선물 카테고리
            3 -> LuxuryFragment()      // 럭셔리 카테고리
            else -> CoupleFragment()   // 연인 카테고리
        }
    }
}