package com.example.chapter5_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class GiftFragment : Fragment() {

    lateinit var giftRecommendBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_gift, container, false)

        giftRecommendBtn = view.findViewById(R.id.gift_recommend_btn)

        // 선물 추천받으러 가기 버튼 클릭 시 질문지 페이지로 이동 (임시)
        giftRecommendBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, QuestionFragment()).commitAllowingStateLoss()
        }

        return view
    }

}