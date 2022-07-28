package com.example.chapter5_5
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainBnv: BottomNavigationView

    //db
    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase


 //    var dbManager= DBManager(this,"my",null,1)
//    lateinit var sqlitedb: SQLiteDatabase
//    public var GiftDatasTemp = ArrayList<MyGiftList>()



    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Chapter55)
        super.onCreate(savedInstanceState)
        //db

//        var adapter =MyGiftListAdapter()
//       adapter.giftList.addAll(dbManager.selectProDuct())
//        adapter.dbManager=dbManager
//        MyGiftItemRv.adapter = adapter

        dbManager = DBManager(this,"my",null,1)
        sqlitedb =dbManager.readableDatabase
//
//        sqlitedb =dbManager.writableDatabase
//        sqlitedb.execSQL("INSERT INTO my VALUES('\"ooo님을 위한 패션 아이템\"','\"wonder visitor 볼캡\"',12,000,'\"wonder_visitor_ball_cap\"');")
//        sqlitedb.execSQL("INSERT INTO my VALUES('\"111님을 위한 패션 아이템\"','\"wonder visitor 볼캡\"',12,000,'\"wonder_visitor_ball_cap\"');")
//        sqlitedb.execSQL("INSERT INTO my VALUES('\"222님을 위한 패션 아이템\"','\"wonder visitor 볼캡\"',12,000,'\"wonder_visitor_ball_cap\"');")
//        sqlitedb.execSQL("INSERT INTO my VALUES('\"333님을 위한 패션 아이템\"','\"wonder visitor 볼캡\"',12,000,'\"wonder_visitor_ball_cap\"');")
//        sqlitedb.execSQL("INSERT INTO my VALUES('\"444님을 위한 패션 아이템\"','\"wonder visitor 볼캡\"',12,000,'\"wonder_visitor_ball_cap\"');")
//        sqlitedb.execSQL("INSERT INTO my VALUES('\"ooo님을 위한 패션 아이템\"','\"wonder visitor 볼캡2\"',12,000,'\"wonder_visitor_ball_cap\"');")
//        sqlitedb.close()
//        //db 읽기
//        dbManager = DBManager(this,"personnelDB",null,1)
//        sqlitedb =dbManager.readableDatabase
//        var cursor: Cursor
//        cursor =sqlitedb.rawQuery("SELECT * FROM my WHERE friendItem ='ooo님을 위한 패션 아이템'",null)
//        //cursor.getString(cursor.getColumnIndex("name")).toString()
//
//        while(cursor.moveToNext()){
//
//            var friendName =cursor.getString((cursor.getColumnIndex("friendName"))).toString()
//            var itemName =cursor.getString((cursor.getColumnIndex("itemName"))).toString()
//            var price =cursor.getString((cursor.getColumnIndex("price"))).toString()
//            var imgText  =cursor.getString((cursor.getColumnIndex("imgText"))).toString()
//            // var str_name =cursor.getString(cursor.getColumnIndex("name")).toString()
//            GiftDatasTemp.apply{
//                add(MyGiftList(cursor.getString(1),itemName,price, R.drawable.wonder_visitor_ball_cap))
//            }
//
//        }
//        cursor.close()
//        sqlitedb.close()
//        dbManager.close()
//
//        val intent= Intent(this,MyFragment::class.java)
//        intent.putExtra("intent_name",GiftDatasTemp)
//        startActivity(intent)

        //db


        setContentView(R.layout.activity_main)

        mainBnv = findViewById(R.id.main_bnv)

        initBottomNavigation()
    }
    @Override
    fun insert(arg0:String,arg1:String,arg2:String,table:String){
        sqlitedb =dbManager.writableDatabase
        sqlitedb.execSQL("INSERT INTO my VALUES('"+arg0+"','"+arg1+"','"+arg2+"');")
//"INSERT INTO personnel VALUES('"+str_name+"','"+str_gender+"',"+str_age+",'"+str_tel+"');")
   }

    fun setDataAtFragment(fragment: Fragment, result:String) {
        val bundle = Bundle()
        bundle.putString("result", result)

        fragment.arguments = bundle
        setFragment(fragment)
    }
    //데이터가 셋팅된 프래그먼트 띄우기
    fun setFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_frm, fragment)
        transaction.commit()
    }

    private fun initBottomNavigation() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()
        mainBnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.giftFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, GiftFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.tasteFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, TasteFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.myFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, MyFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}

