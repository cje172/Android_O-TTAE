package com.example.chapter5_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class PriceFiveFragment : Fragment() {

    private var productData = ArrayList<Product>()
    lateinit var priceFiveProductRv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_price, container, false)

        priceFiveProductRv = view.findViewById(R.id.price_product_rv)

        productData.apply {
            add(Product("모던포인트", "원목 유선 플랜테리어 식물 무드등 LED", "55,000", R.drawable.product_list_mood_light_img))
            add(Product("러쉬", "더티 보디 스프레이 200ml", "60,000", R.drawable.product_list_lush_img))
            add(Product("말본골프", "Bucket 햇 WHITE", "99,000", R.drawable.product_list_hat_img))
            add(Product("Apple", "에어팟 프로 (맥세이프 충전 케이스)", "269,000", R.drawable.product_list_airpot_img))
        }

        // 어댑터와 데이터 리스트 연결
        val priceAllProductRVAdapter = ProductRVAdapter(productData)
        priceFiveProductRv.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        priceFiveProductRv.adapter = priceAllProductRVAdapter

        return view
    }
}