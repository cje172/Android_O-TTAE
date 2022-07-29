package com.example.chapter5_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ParentsFragment : Fragment() {
    private var productDatas = ArrayList<Product>()
    lateinit var homeParentsProductRv: RecyclerView
    lateinit var homeParentsAllBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_parents, container, false)

        homeParentsProductRv = view.findViewById(R.id.home_parents_product_rv)
        homeParentsAllBtn = view.findViewById(R.id.home_parents_birthday_all_btn)

        val transaction = (context as MainActivity).supportFragmentManager.beginTransaction()

        productDatas.apply {
            add(Product("언폴드", "Copenhagen-bule 에코백", "9,800", R.drawable.product_list_bag_img))
            add(Product("비비디", "드레스 퍼퓸 100ml", "8,950", R.drawable.product_list_perfume_img))
            add(Product("라이프 아카이브", "라이프 아카이브 일회용 카메라", "20,120", R.drawable.product_list_film_img))
            add(Product("코지테이블", "아이보리앤도트 머그잔", "8,400", R.drawable.product_list_cup_img))
        }

        // 어댑터와 데이터 리스트 연결
        val homeParentsRVAdapter = ProductRVAdapter(productDatas)
        homeParentsProductRv.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        homeParentsProductRv.adapter = homeParentsRVAdapter

        // 전체 보기 버튼 클릭 시 상품 리스트 페이지로 이동
        homeParentsAllBtn.setOnClickListener {
            sendData(1)
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