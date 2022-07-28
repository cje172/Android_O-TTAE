package com.example.chapter5_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class TakerInfoFirstFragment : Fragment() {

    lateinit var takerInfoFirstNextBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_taker_info_first, container, false)

        takerInfoFirstNextBtn = view.findViewById(R.id.taker_info_first_next_btn)

        val transaction = (context as MainActivity).supportFragmentManager.beginTransaction()

        // 다음 버튼 클릭 시 두번째 받는이 정보 입력 페이지로 이동
        takerInfoFirstNextBtn.setOnClickListener {
            transaction.replace(R.id.main_frm, TakerInfoSecondFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view
    }

}