package com.example.chapter5_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class PriceAllFragment : Fragment() {

    private var productData = ArrayList<Product>()
    private lateinit var priceAllProductRv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_price, container, false)

        priceAllProductRv = view.findViewById(R.id.price_product_rv)

        productData.apply {
            add(Product("양키캔들", "우드윅 미니 캔들 + 루나 스몰 캔들워머 + 전구 2개", "29,900", R.drawable.product_list_candle_img))
            add(Product("러쉬", "더티 보디 스프레이 200ml", "60,000", R.drawable.product_list_lush_img))
            add(Product("코튼랩", "수건선물세트 40수 8매", "44,000", R.drawable.product_list_towel_img))
            add(Product("임페리얼", "데니쉬버터쿠키셀렉션 500g(대용량)", "9,900", R.drawable.product_list_snack_img))
            add(Product("에이치앤프렌즈", "\"오둥이 입니다만\" 오둥이 모찌 바디필로우", "29,800", R.drawable.product_list_pillow_img))
            add(Product("말본골프", "Bucket 햇 WHITE", "99,000", R.drawable.product_list_hat_img))
            add(Product("클럽클리오", "페리페라 잉크 더 에어리벨벳 물복딱복", "7,200", R.drawable.product_list_tint_img))
            add(Product("EGOIDSM", "미니 빔프로젝터 무드 세트", "9,800", R.drawable.product_list_beam_img))
            add(Product("베리베리", "수제과일청 선물세트 (레몬청 + 자몽청)", "30,300", R.drawable.product_list_fruit_syrup_img))
            add(Product("solful", "내열 이중유리컵 동물컵 글라스 280ml", "8,500", R.drawable.product_list_animal_cup_img))
            add(Product("Apple", "에어팟 프로 (맥세이프 충전 케이스)", "269,000", R.drawable.product_list_airpot_img))
            add(Product("모던포인트", "원목 유선 플랜테리어 식물 무드등 LED", "55,000", R.drawable.product_list_mood_light_img))
        }

        // 어댑터와 데이터 리스트 연결
        val priceAllProductRVAdapter = ProductRVAdapter(productData)
        priceAllProductRv.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        priceAllProductRv.adapter = priceAllProductRVAdapter

        return view
    }
}