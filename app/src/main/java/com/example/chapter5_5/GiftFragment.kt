package com.example.chapter5_5

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class GiftFragment : Fragment() {

    lateinit var giftRecommendBtn: Button
    lateinit var giftCategoryBirthdayView: View
    lateinit var giftCategoryParentsView: View
    lateinit var giftCategoryLightGiftView: View
    lateinit var giftCategoryLuxuryView: View

    lateinit var tabValue: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_gift, container, false)

        giftRecommendBtn = view.findViewById(R.id.gift_recommend_btn)
        giftCategoryBirthdayView = view.findViewById(R.id.gift_category_birthday_view)
        giftCategoryParentsView = view.findViewById(R.id.gift_category_parents_view)
        giftCategoryLightGiftView = view.findViewById(R.id.gift_category_light_gift_view)
        giftCategoryLuxuryView = view.findViewById(R.id.gift_category_luxury_view)

        val transaction = (context as MainActivity).supportFragmentManager.beginTransaction()

        // 선물 추천받으러 가기 버튼 클릭 시 받는이 정보 입력 페이지로 이동
        giftRecommendBtn.setOnClickListener {
            transaction.replace(R.id.main_frm, TakerInfoFirstFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }


        // 각 카테고리 버튼 클릭 시 상품 리스트 페이지로 이동
        giftCategoryBirthdayView.setOnClickListener {
            sendData(0)
            transaction.replace(R.id.main_frm, ProductFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
        giftCategoryParentsView.setOnClickListener {
            sendData(1)
            transaction.replace(R.id.main_frm, ProductFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
        giftCategoryLightGiftView.setOnClickListener {
            sendData(2)
            transaction.replace(R.id.main_frm, ProductFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
        giftCategoryLuxuryView.setOnClickListener {
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