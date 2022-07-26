package com.example.chapter5_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class TasteFragment : Fragment() {

    lateinit var tasteQuestionBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_taste, container, false)

        tasteQuestionBtn = view.findViewById(R.id.taste_question_btn)

        // 취향 질문지 버튼 클릭 시 질문지 페이지로 이동 (임시)
        tasteQuestionBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, QuestionFragment()).commitAllowingStateLoss()
        }


        return view
    }

}