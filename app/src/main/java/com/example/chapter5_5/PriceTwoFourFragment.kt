package com.example.chapter5_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class PriceTwoFourFragment : Fragment() {

    private var productData = ArrayList<Product>()
    lateinit var priceTwoFourProductRv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_price, container, false)

        priceTwoFourProductRv = view.findViewById(R.id.price_product_rv)

        productData.apply {
            add(Product("양키캔들", "우드윅 미니 캔들 + 루나 스몰 캔들워머 + 전구 2개", "29,900", R.drawable.product_list_candle_img))
            add(Product("코튼랩", "수건선물세트 40수 8매", "44,000", R.drawable.product_list_towel_img))
            add(Product("에이치앤프렌즈", "\"오둥이 입니다만\" 오둥이 모찌 바디필로우", "29,800", R.drawable.product_list_pillow_img))
            add(Product("베리베리", "수제과일청 선물세트 (레몬청 + 자몽청)", "30,300", R.drawable.product_list_fruit_syrup_img))
        }

        // 어댑터와 데이터 리스트 연결
        val priceAllProductRVAdapter = ProductRVAdapter(productData)
        priceTwoFourProductRv.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        priceTwoFourProductRv.adapter = priceAllProductRVAdapter

        return view
    }
}