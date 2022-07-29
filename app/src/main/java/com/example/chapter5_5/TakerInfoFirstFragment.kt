package com.example.chapter5_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class TakerInfoFirstFragment : Fragment() {

    lateinit var takerInfoFirstNextBtn: Button
    lateinit var name: EditText
    private var friendId: String? = null    // 전역변수로 사용

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_taker_info_first, container, false)

        takerInfoFirstNextBtn = view.findViewById(R.id.taker_info_first_next_btn)
        name = view.findViewById(R.id.taker_info_first_name_edt)

        val transaction = (context as MainActivity).supportFragmentManager.beginTransaction()

        // 다음 버튼 클릭 시 두 번째 받는이 정보 입력 페이지로 이동
        takerInfoFirstNextBtn.setOnClickListener {
            friendId = name.text.toString()

            // 두 번째 받는이 정보 입력 페이지, 결과지 페이지로 넘길 친구 이름 저장
            saveData(name.text.toString())

            transaction.replace(R.id.main_frm, TakerInfoSecondFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        return view
    }

    private fun saveData(friendName: String) {
        var pref = this.activity?.getPreferences(0)
        var editor = pref?.edit()

        editor?.putString("friendName", friendName)?.apply()
    }
}