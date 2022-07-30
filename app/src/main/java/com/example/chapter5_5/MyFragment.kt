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
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class MyFragment : Fragment() {

    lateinit var Santa: FrameLayout
    lateinit var mainActivity: MainActivity
    private var GiftDatas = ArrayList<MyGiftList>()
    lateinit var MyGiftItemRv: RecyclerView
    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase
    private var userId: String? = null
    lateinit var someId: TextView
    lateinit var logOutBtn: Button

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // 2. Context를 액티비티로 형변환해서 할당
        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setFragmentResultListener("requestKey") { requestKey, bundle ->
//            val result = bundle.getString("bundleKey")
//            // Do something with the result
//        }

        arguments?.let {
            userId = it.getString("userId")    //데이터 수신
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
        // cursor =sqlitedb.rawQuery("SELECT * FROM my WHERE user = 'qqq';",null)

        // 지워야함
        userId = "1234"
        cursor = sqlitedb.rawQuery("SELECT * FROM my WHERE user='" + userId + "';", null)


        // 사용자 이름 받아오기
        loadUserName()

        while (cursor.moveToNext()) {
            var friendName = cursor.getString((cursor.getColumnIndex("friendName"))).toString()
            var itemName = cursor.getString((cursor.getColumnIndex("itemName"))).toString()
            var price = cursor.getString((cursor.getColumnIndex("price"))).toString()
            var img = cursor.getInt((cursor.getColumnIndex("coverImg")))
            GiftDatas.apply {
                add(MyGiftList(friendName, itemName, price, img!!))
//            add(MyGiftList("111님을 위한 머그잔", "아이보리앤도트 머그잔", "8,400", R.drawable.list2_cup_img))
//            add(MyGiftList("222님을 위한 패션아이템", "Copenhagen-bule 에코백", "9,800", R.drawable.list3_bag_img))
//            add(MyGiftList("333님을 위한 스몰 력셔리", "드레스 퍼퓸 100ml", "8,950", R.drawable.list4_perfume_img))
//            add(MyGiftList("444님을 위한 스몰 력셔리", "드레스 퍼퓸 100ml", "8,950", R.drawable.list4_perfume_img))
//            add(MyGiftList("555님을 위한 스몰 력셔리", "드레스 퍼퓸 100ml", "8,950", R.drawable.list4_perfume_img))
//            add(MyGiftList("666님을 위한 패션 아이템", "wonder visitor 볼캡", "12,000", R.drawable.wonder_visitor_ball_cap))
            }

        }

        MyGiftItemRv = view.findViewById(R.id.my_gift_list_rv)
        //  Santa = view.findViewById(R.id.santa)
//        GiftDatas.apply {
//            add(MyGiftList("ooo님을 위한 패션 아이템", "wonder visitor 볼캡", "12,000", R.drawable.wonder_visitor_ball_cap))
//            add(MyGiftList("111님을 위한 머그잔", "아이보리앤도트 머그잔", "8,400", R.drawable.list2_cup_img))
//            add(MyGiftList("222님을 위한 패션아이템", "Copenhagen-bule 에코백", "9,800", R.drawable.list3_bag_img))
//            add(MyGiftList("333님을 위한 스몰 력셔리", "드레스 퍼퓸 100ml", "8,950", R.drawable.list4_perfume_img))
//            add(MyGiftList("444님을 위한 스몰 력셔리", "드레스 퍼퓸 100ml", "8,950", R.drawable.list4_perfume_img))
//            add(MyGiftList("555님을 위한 스몰 력셔리", "드레스 퍼퓸 100ml", "8,950", R.drawable.list4_perfume_img))
//            add(MyGiftList("666님을 위한 패션 아이템", "wonder visitor 볼캡", "12,000", R.drawable.wonder_visitor_ball_cap))
//        }
//        GiftDatas.apply {
//            add(MyGiftList(friendName, itemName, price,img!!))
////            add(MyGiftList("111님을 위한 머그잔", "아이보리앤도트 머그잔", "8,400", R.drawable.list2_cup_img))
////            add(MyGiftList("222님을 위한 패션아이템", "Copenhagen-bule 에코백", "9,800", R.drawable.list3_bag_img))
////            add(MyGiftList("333님을 위한 스몰 력셔리", "드레스 퍼퓸 100ml", "8,950", R.drawable.list4_perfume_img))
////            add(MyGiftList("444님을 위한 스몰 력셔리", "드레스 퍼퓸 100ml", "8,950", R.drawable.list4_perfume_img))
////            add(MyGiftList("555님을 위한 스몰 력셔리", "드레스 퍼퓸 100ml", "8,950", R.drawable.list4_perfume_img))
////            add(MyGiftList("666님을 위한 패션 아이템", "wonder visitor 볼캡", "12,000", R.drawable.wonder_visitor_ball_cap))
//        }


        val myRVAdapter = MyGiftListAdapter(GiftDatas)
        MyGiftItemRv.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        MyGiftItemRv.adapter = myRVAdapter


        return view

//    private fun transXAni() {
//        btn_transX_left.setOnClickListener {
//            btn_transX_left.animate().translationX(-400f * dp).setDuration(duration).withEndAction {
//                btn_transX_left.translationX = 0f
//            }.start()
//        }
//        btn_transX_right.setOnClickListener {
//            btn_transX_right.animate().translationX(400f * dp).setDuration(duration).withEndAction {
//                btn_transX_right.translationX = 0f
//            }.start()
//
//        }
//    }

    }

    private fun loadUserName() {
        var pref = this.activity?.getSharedPreferences("name", 0)
        var name = pref?.getString("name", "0")

        someId.text = name + "님의 선물 보따리"
    }
}