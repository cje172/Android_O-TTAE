package com.example.o_ttae.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.o_ttae.ui.product.price.ProductPriceVPAdapter
import com.example.o_ttae.R
import com.example.o_ttae.data.model.WeekProduct
import com.example.o_ttae.data.adapter.WeekProductRVAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.ArrayList

class ProductLuxuryFragment : Fragment() {

    private lateinit var productLuxuryWeekRv: RecyclerView
    private var weekProductData = ArrayList<WeekProduct>()

    private lateinit var productCategoryPriceTb: TabLayout
    lateinit var productCategoryPriceVp: ViewPager2
    private val productCategoryTab = arrayListOf("전체", "1만원 이하", "2~4만원대", "5만원 이상")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_product_category, container, false)

        productLuxuryWeekRv = view.findViewById(R.id.product_category_week_rv)
        productCategoryPriceTb = view.findViewById(R.id.product_category_price_tb)
        productCategoryPriceVp = view.findViewById(R.id.product_category_price_vp)

        // 가격대별 인기선물 TabLayout, ViewPager2 연결
        val productPriceFm = childFragmentManager
        val productPriceLifecycle = viewLifecycleOwner.lifecycle
        val productPriceVPAdapter =
            ProductPriceVPAdapter(productPriceFm, productPriceLifecycle)
        productCategoryPriceVp.adapter = productPriceVPAdapter

        TabLayoutMediator(productCategoryPriceTb, productCategoryPriceVp) { tab, position ->
            tab.text = productCategoryTab[position]
        }.attach()

        productCategoryPriceVp.isUserInputEnabled = false
        productCategoryPriceTb.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let { productCategoryPriceVp.setCurrentItem(it, false) }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })


        // 금주의 이거 어때? 데이터 리스트
        weekProductData.apply {
            add(WeekProduct("DIOR", "[각인/선물포장] 립 글로우", R.drawable.product_list_dior_img))
            add(WeekProduct("젠틀몬스터", "릭 01", R.drawable.product_list_sunglasses_img))
            add(WeekProduct("판도라", "신탄생석 참 목걸이세트", R.drawable.product_list_pandora_img))
            add(WeekProduct("샤넬", "블루 드 샤넬 오 드 빠르펭 50ml", R.drawable.product_list_chanel_img))
        }

        // 어댑터와 데이터 리스트 연결
        val luxuryWeekProductRVAdapter = WeekProductRVAdapter(weekProductData)
        productLuxuryWeekRv.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL, false
        )
        productLuxuryWeekRv.adapter = luxuryWeekProductRVAdapter

        return view
    }
}