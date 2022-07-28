package com.example.chapter5_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import java.util.ArrayList

class ProductParentsFragment : Fragment() {

    lateinit var productParentsWeekRv: RecyclerView
    private var weekProductDatas = ArrayList<WeekProduct>()

//    lateinit var productCategoryTb:TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_product_category, container, false)

        productParentsWeekRv = view.findViewById(R.id.product_category_week_rv)

//        productCategoryTb = view.findViewById(R.id.product_category_tb)

//        val tab: TabLayout.Tab? = productCategoryTb.getTabAt(1)
//        tab?.select()

        weekProductDatas.apply {
            add(WeekProduct("코지테이블", "아이보리앤도트 머그잔", R.drawable.product_list_cup_img))
            add(WeekProduct("언폴드", "Copenhagen-bule 에코백", R.drawable.product_list_bag_img))
            add(WeekProduct("비비디", "드레스 퍼퓸 100ml", R.drawable.product_list_perfume_img))
            add(WeekProduct("라이프 아카이브", "라이프 아카이브 일회용 카메라", R.drawable.product_list_film_img))
        }

        // 어댑터와 데이터 리스트 연결
        val parentsWeekProductRVAdapter = WeekProductRVAdapter(weekProductDatas)
        productParentsWeekRv.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL, false
        )
        productParentsWeekRv.adapter = parentsWeekProductRVAdapter


        return view

    }

}