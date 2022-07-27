package com.example.chapter5_5

import android.os.Bundle
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import java.util.*




class MyFragment : Fragment() {
    lateinit var Santa: FrameLayout
    lateinit var mainActivity: MainActivity
    private var GiftDatas = ArrayList<MyGiftList>()
    lateinit var MyGiftItemRv: RecyclerView
    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase


    override fun onAttach(context: Context) {
        super.onAttach(context)

        // 2. Context를 액티비티로 형변환해서 할당
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_my, container, false)

        //db 읽어서 배열에 저장
        dbManager = DBManager(mainActivity,"my",null,1)
        sqlitedb =dbManager.readableDatabase
        var cursor: Cursor
        //cursor =sqlitedb.rawQuery("SELECT * FROM my WHERE user = 'qqq';",null)
        cursor =sqlitedb.rawQuery("SELECT * FROM my WHERE user='qqq';",null)


        while(cursor.moveToNext()) {
            var friendName = cursor.getString((cursor.getColumnIndex("friendName"))).toString()
             var itemName = cursor.getString((cursor.getColumnIndex("itemName"))).toString()
             var price = cursor.getString((cursor.getColumnIndex("price"))).toString()
             var img =cursor.getInt((cursor.getColumnIndex("coverImg")))
            GiftDatas.apply {
                add(MyGiftList(friendName, itemName, price,img!!))
//            add(MyGiftList("111님을 위한 머그잔", "아이보리앤도트 머그잔", "8,400", R.drawable.list2_cup_img))
//            add(MyGiftList("222님을 위한 패션아이템", "Copenhagen-bule 에코백", "9,800", R.drawable.list3_bag_img))
//            add(MyGiftList("333님을 위한 스몰 력셔리", "드레스 퍼퓸 100ml", "8,950", R.drawable.list4_perfume_img))
//            add(MyGiftList("444님을 위한 스몰 력셔리", "드레스 퍼퓸 100ml", "8,950", R.drawable.list4_perfume_img))
//            add(MyGiftList("555님을 위한 스몰 력셔리", "드레스 퍼퓸 100ml", "8,950", R.drawable.list4_perfume_img))
//            add(MyGiftList("666님을 위한 패션 아이템", "wonder visitor 볼캡", "12,000", R.drawable.wonder_visitor_ball_cap))
            }

        }

        MyGiftItemRv=view.findViewById(R.id.my_gift_list_rv)
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
//
        return view
//         Inflate the layout for this fragment
//         return inflater.inflate(R.layout.fragment_my, container, false)
//          }

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

}