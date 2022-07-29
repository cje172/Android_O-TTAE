package com.example.chapter5_5

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProductFragment : Fragment() {

    lateinit var productCategoryTb: TabLayout
    lateinit var productCategoryVp: ViewPager2

    private val productCategoryTab = arrayListOf("생일", "부모님", "가벼운 선물", "럭셔리", "연인")
    private var resultTab: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_product, container, false)

        productCategoryTb = view.findViewById(R.id.product_category_tb)
        productCategoryVp = view.findViewById(R.id.product_category_vp)

        // 상품 리스트 화면 카테고리 - TabLayout, ViewPager2 연결
        val productCategoryFm = childFragmentManager
        val productCategoryLifecycle = viewLifecycleOwner.lifecycle
        val productCategoryAdapter =
            ProductCategoryVPAdapter(productCategoryFm, productCategoryLifecycle)
        productCategoryVp.adapter = productCategoryAdapter

        TabLayoutMediator(productCategoryTb, productCategoryVp) { tab, position ->
            tab.text = productCategoryTab[position]
        }.attach()

        productCategoryVp.isUserInputEnabled = false
        productCategoryTb.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let { productCategoryVp.setCurrentItem(it, false) }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        // 비밀선물 화면에서 클릭한 카테고리에 해당하는 탭으로 이동
        receiveData()
        val tab: TabLayout.Tab? = productCategoryTb.getTabAt(resultTab)
        tab?.select()


        return view
    }

    fun receiveData() {
        var pref = this.activity?.getPreferences(0)
        var tab = pref?.getInt("tabPosition", 0)

        if (tab != null) {
            resultTab = tab
        }
    }
}