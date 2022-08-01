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

class CoupleFragment : Fragment() {
    private var productDatas = ArrayList<Product>()
    lateinit var homeCoupleProductRv: RecyclerView
    lateinit var homeCoupleAllBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_couple, container, false)

        homeCoupleProductRv = view.findViewById(R.id.home_couple_product_rv)
        homeCoupleAllBtn = view.findViewById(R.id.home_couple_all_btn)

        val transaction = (context as MainActivity).supportFragmentManager.beginTransaction()

        productDatas.apply {
            add(Product("JW중외제약", "피톤케어360 차량용 방향제", "42,000", R.drawable.product_list_air_freshener_img))
            add(Product("터틀힙", "Lettering 벚꽃크림 케이크", "59,000", R.drawable.product_list_cake_img))
            add(Product("DOOSI", "[생화] 피치살몬 꽃다발", "35,900", R.drawable.producst_list_flower_img))
            add(Product("하이미엘", "천연 꿀버터 뚱카롱(마카롱) 10구 선물패키지", "22,900", R.drawable.product_list_macaroon_img))
        }

        // 어댑터와 데이터 리스트 연결
        val homeCoupleRVAdapter = ProductRVAdapter(productDatas)
        homeCoupleProductRv.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        homeCoupleProductRv.adapter = homeCoupleRVAdapter


        // 전체 보기 버튼 클릭 시 상품 리스트 페이지로 이동
        homeCoupleAllBtn.setOnClickListener {
            sendData(4)
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