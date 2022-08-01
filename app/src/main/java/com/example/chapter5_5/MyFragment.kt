package com.example.chapter5_5

import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MyFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    private var giftData = ArrayList<MyGiftList>()
    lateinit var myGiftItemRv: RecyclerView
    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase
    private var userId: String? = null
    lateinit var someId: TextView
    lateinit var logOutBtn: Button

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            userId = it.getString("userId")  // 데이터 수신
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_my, container, false)

        // 사용자 이름 설정
        someId = view.findViewById(R.id.some_id)
        logOutBtn = view.findViewById(R.id.Log_out_button)
        logOutBtn.setOnClickListener {
            startActivity(Intent(mainActivity, LoginPageActivity::class.java))
        }

        // db 읽어서 배열에 저장
        dbManager = DBManager(mainActivity, "my", null, 1)
        sqlitedb = dbManager.readableDatabase
        var cursor: Cursor

        // 현재 로그인 한 user의 아이디를 가져오기
        loadUserName()
        // 가져온 user의 아이디를 사용하여 my 테이블에서 user의 선물추천 받은 기록을 불러오기
        cursor = sqlitedb.rawQuery("SELECT * FROM my WHERE user='" + userId + "';", null)


        // 기록 개수만큼 GiftDatas(ArrayList)에 저장하기
        while (cursor.moveToNext()) {
            var friendName = cursor.getString((cursor.getColumnIndex("friendName"))).toString()
            var itemName = cursor.getString((cursor.getColumnIndex("itemName"))).toString()
            var price = cursor.getString((cursor.getColumnIndex("price"))).toString()
            var img = cursor.getInt((cursor.getColumnIndex("coverImg")))
            giftData.apply {
                add(MyGiftList(friendName, itemName, price, img!!))

            }

        }

        // fragment_my의 recyclerview와 연결
        myGiftItemRv = view.findViewById(R.id.my_gift_list_rv)

        val myRVAdapter = MyGiftListAdapter(giftData)
        myGiftItemRv.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        myGiftItemRv.adapter = myRVAdapter

        return view
    }

    // 로그인한 사용자 이름 가져와서 my페이지에 문구 세팅
    private fun loadUserName() {
        var pref = this.activity?.getSharedPreferences("name", 0)
        userId = pref?.getString("name", "0")

        someId.text = userId + "님의 선물 보따리"
    }
}