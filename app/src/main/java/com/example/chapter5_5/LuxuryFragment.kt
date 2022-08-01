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

class LuxuryFragment : Fragment() {
    private var productDatas = ArrayList<Product>()
    lateinit var homeLuxuryProductRv: RecyclerView
    lateinit var homeLuxuryAllBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_luxury, container, false)

        homeLuxuryProductRv = view.findViewById(R.id.home_luxury_product_rv)
        homeLuxuryAllBtn = view.findViewById(R.id.home_luxury_all_btn)

        val transaction = (context as MainActivity).supportFragmentManager.beginTransaction()

        productDatas.apply {
            add(Product("DIOR", "[각인/선물포장] 립 글로우", "47,000", R.drawable.product_list_dior_img))
            add(Product("젠틀몬스터", "릭 01", "259,000", R.drawable.product_list_sunglasses_img))
            add(Product("판도라", "신탄생석 참 목걸이세트", "98,000", R.drawable.product_list_pandora_img))
            add(Product("샤넬", "블루 드 샤넬 오 드 빠르펭 50ml", "127,000", R.drawable.product_list_chanel_img))
        }

        // 어댑터와 데이터 리스트 연결
        val homeLuxuryRVAdapter = ProductRVAdapter(productDatas)
        homeLuxuryProductRv.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        homeLuxuryProductRv.adapter = homeLuxuryRVAdapter


        // 전체 보기 버튼 클릭 시 상품 리스트 페이지로 이동
        homeLuxuryAllBtn.setOnClickListener {
            sendData(3)
            transaction.replace(R.id.main_frm, ProductFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view
    }

    private fun sendData(tabPosition: Int) {
        var pref = this.activity?.getPreferences(0)
        var editor = pref?.edit()

        editor?.putInt("tabPosition", tabPosition)?.apply()
    }
}