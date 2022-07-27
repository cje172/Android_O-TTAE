package com.example.chapter5_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProductListFragment : Fragment() {

    lateinit var productListCategoryTb: TabLayout
    lateinit var productListCategoryVp: ViewPager2

    private val productListCategoryTab = arrayListOf("생일", "부모님", "가벼운 선물", "럭셔리", "연인")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_product_list, container, false)

        productListCategoryTb = view.findViewById(R.id.product_list_category_tb)
        productListCategoryVp = view.findViewById(R.id.product_list_category_vp)

        // 상품 리스트 화면 카테고리 - TabLayout, ViewPager2 연결
        val productListCategoryFm = childFragmentManager
        val productListCategoryLifecycle = viewLifecycleOwner.lifecycle
        val productListCategoryAdapter =
            ProductListCategoryVPAdapter(productListCategoryFm, productListCategoryLifecycle)
        productListCategoryVp.adapter = productListCategoryAdapter

        TabLayoutMediator(productListCategoryTb, productListCategoryVp) { tab, position ->
            tab.text = productListCategoryTab[position]
        }.attach()

        return view
    }

}