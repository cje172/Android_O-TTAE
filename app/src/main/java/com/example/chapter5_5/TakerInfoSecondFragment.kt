package com.example.chapter5_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class TakerInfoSecondFragment : Fragment() {

    lateinit var takerInfoSecondNextBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_taker_info_second, container, false)

        takerInfoSecondNextBtn = view.findViewById(R.id.taker_info_second_next_btn)

        val transaction = (context as MainActivity).supportFragmentManager.beginTransaction()

        // 다음 버튼 클릭 시 질문지 페이지로 이동
        takerInfoSecondNextBtn.setOnClickListener {
            transaction.replace(R.id.main_frm, QuestionFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view
    }

}