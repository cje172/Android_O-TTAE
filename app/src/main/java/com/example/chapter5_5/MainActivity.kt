package com.example.chapter5_5

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var mainBnv: BottomNavigationView

    var userName: String? = null

    // db
    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Chapter55)
        super.onCreate(savedInstanceState)

        // db
        dbManager = DBManager(this, "my", null, 1)
        sqlitedb = dbManager.readableDatabase

        setContentView(R.layout.activity_main)

        mainBnv = findViewById(R.id.main_bnv)

        initBottomNavigation()
        loadUserName()
        sendUserName(userName.toString())
    }

    @Override
    fun insert(arg0: String, arg1: String, arg2: String) {
        sqlitedb = dbManager.writableDatabase
        sqlitedb.execSQL("INSERT INTO my VALUES('" + arg0 + "','" + arg1 + "','" + arg2 + "');")
    }

    fun setDataAtFragment(fragment: Fragment, result: String) {
        val bundle = Bundle()
        bundle.putString("result", result)

        fragment.arguments = bundle
        setFragment(fragment)
    }

    // 데이터가 셋팅된 프래그먼트 띄우기
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

    // 로그인한 사용자 이름 받기
    private fun loadUserName() {
        var pref = applicationContext.getSharedPreferences("name", 0)
        var name = pref?.getString("name", "")

        userName = name
    }

    // 로그인한 사용자 이름 전달
    private fun sendUserName(name: String) {
        var pref = applicationContext.getSharedPreferences("name", 0)
        var editor = pref?.edit()

        editor?.putString("name", name)?.apply()
    }
}