package com.example.chapter5_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class LightGiftFragment : Fragment() {

    private var productDatas = ArrayList<Product>()
    lateinit var homeLightGiftProductRv: RecyclerView
    lateinit var homeLightGiftAllBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_light_gift, container, false)

        homeLightGiftProductRv = view.findViewById(R.id.home_light_gift_product_rv)
        homeLightGiftAllBtn = view.findViewById(R.id.home_light_gift_all_btn)

        val transaction = (context as MainActivity).supportFragmentManager.beginTransaction()

        productDatas.apply {
            add(Product("대봉식품", "둘이 함께 먹는 매일견과 20g x 60봉", "25,430", R.drawable.product_list_nut_img))
            add(Product("경남제약", "카카오 레모나산 2g 100포", "17,450", R.drawable.product_list_lemona_img))
            add(Product("캐릭터주방", "돌아온 스누피 댄스댄스 글라스잔 2P세트", "17,850", R.drawable.product_list_glass_img))
            add(Product("원더커피", "더치커피 5종원두 혼합 100팩", "18,900", R.drawable.product_list_coffee_img))
        }

        // 어댑터와 데이터 리스트 연결
        val homeLightGiftRVAdapter = ProductRVAdapter(productDatas)
        homeLightGiftProductRv.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        homeLightGiftProductRv.adapter = homeLightGiftRVAdapter


        // 전체 보기 버튼 클릭 시 상품 리스트 페이지로 이동
        homeLightGiftAllBtn.setOnClickListener {
            sendData(2)
            transaction.replace(R.id.main_frm, ProductFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view
    }

    // 이동할 카테고리 탭 전달
    private fun sendData(tabPosition: Int) {
        var pref = this.activity?.getPreferences(0)
        var editor = pref?.edit()

        editor?.putInt("tabPosition", tabPosition)?.apply()
    }
}