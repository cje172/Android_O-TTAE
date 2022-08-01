package com.example.chapter5_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class TakerInfoSecondFragment : Fragment() {

    lateinit var takerInfoSecondNextBtn: Button
    lateinit var takerInfoSecondTitleTv: TextView

    private var name: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_taker_info_second, container, false)

        takerInfoSecondNextBtn = view.findViewById(R.id.taker_info_second_next_btn)
        takerInfoSecondTitleTv = view.findViewById(R.id.taker_info_second_title_tv)

        val transaction = (context as MainActivity).supportFragmentManager.beginTransaction()

        // 첫 번째 받는이 정보 입력 페이지에서 저장한 친구 이름 불러오기
        loadData()

        // 다음 버튼 클릭 시 질문지 페이지로 이동
        takerInfoSecondNextBtn.setOnClickListener {
            transaction.replace(R.id.main_frm, QuestionFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view
    }


    private fun loadData() {
        var pref = this.activity?.getPreferences(0)
        var name = pref?.getString("friendName", "")

        takerInfoSecondTitleTv.text = name + "님을 위한 \n선물 기본 정보를 입력해주세요"
    }
}