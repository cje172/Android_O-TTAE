package com.example.chapter5_5

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment

class RegisterPageActivity : AppCompatActivity() {


    lateinit var regButton: ImageButton
    lateinit var registerName: EditText
    lateinit var registerEmail: EditText
    lateinit var registerPassword: EditText
    lateinit var registerRePassword: EditText
    lateinit var passwordMismatch: TextView

    //db
    lateinit var dbManager: DBManager
    lateinit var sqlitedbW: SQLiteDatabase
    lateinit var sqlitedbR: SQLiteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

        regButton = findViewById(R.id.regButton)
        registerName = findViewById(R.id.register_name)
        registerEmail = findViewById(R.id.register_email)
        registerPassword = findViewById(R.id.register_password)
        registerRePassword = findViewById(R.id.register_rePassword)
        passwordMismatch = findViewById(R.id.password_mismatch)

        //db
        dbManager = DBManager(this, "user", null, 1)
        //쓰기

        //읽기
        sqlitedbR = dbManager.readableDatabase
        sqlitedbW = dbManager.writableDatabase


//        //아이디 중복 확인
//        checkId()
//        //이메일 중복 확인
//        checkEmail()
//        //비밀번호 일치 확인
//        checkPassword()
        //db에 저장
        //아이디 Myframent로 넘기기
        regButton.setOnClickListener {
            //로그인 화면으로 이동
            //아이디 중복 확인
            // checkId()
            //이메일 중복 확인
            // checkEmail()
            //비밀번호 일치 확인
            // checkPassword()

//        var cursor: Cursor
//        var userId:String=register_name.text.toString()
//        cursor =sqlitedbR.rawQuery("SELECT * FROM user WHERE name='$userId';",null)
//        //  var friendName = cursor.getString((cursor.getColumnIndex("friendName"))).toString()
//        //아이디 중복 확인
//        if(cursor.count>0)//중복있음
//        {
//            register_name.hint = "이미 있는 아이디 입니다."
//
//        }
            //     startActivity(Intent(this, MainActivity::class.java))
            if (checkId() && checkEmail() && checkPassword()) {
                //db에 정보 저장

                sqlitedbW.execSQL("INSERT INTO user VALUES('" + registerName.text.toString() + "','" + registerEmail.text.toString() + "','" + registerPassword.text.toString() + "');")
                //MyFragment에 유저 이름 전달
                //   setDataAtFragment(MyFragment(), register_name.text.toString())
                //ResultFragment에 유저 이름 전달
                // setDataAtFragment2(ResultFragment(), register_name.text.toString())
//            if(checkEmail())
//                startActivity(Intent(this, MainActivity::class.java))
                startActivity(Intent(this, LoginPageActivity::class.java))

                //  finish()
            }
        }
    }

    private fun checkId(): Boolean {
        var cursor: Cursor
        var userId: String = registerName.text.toString()
        cursor = sqlitedbR.rawQuery("SELECT * FROM user WHERE name='$userId';", null)
        //  var friendName = cursor.getString((cursor.getColumnIndex("friendName"))).toString()

        // 아이디 중복 확인
        if (cursor.count > 0)  // 중복있음
        {
            // password_mismatch
            passwordMismatch.visibility = View.VISIBLE
            passwordMismatch.text = "이미 있는 아이디 입니다."
            registerName.hint = "이미 있는 아이디 입니다."

            return false
        }
        return true
    }

    private fun checkEmail(): Boolean {
        var cursor: Cursor
        var userEmail: String = registerEmail.text.toString()
        cursor = sqlitedbR.rawQuery("SELECT * FROM user WHERE email='$userEmail';", null)
        //  var friendName = cursor.getString((cursor.getColumnIndex("friendName"))).toString()

        // 이메일 중복 확인
        if (cursor.count > 0)  // 중복있음
        {
            registerEmail.hint = "이미 가입된 이메일 입니다."

            return false
        }
        return true
    }

    private fun checkPassword(): Boolean {
        var password: String = registerPassword.text.toString()
        var password2: String = registerRePassword.text.toString()

        if (!password.equals(password2)) {
            passwordMismatch.visibility = View.VISIBLE

            return false
        } else {
            passwordMismatch.visibility = View.INVISIBLE
            return true
        }

        return true
    }

    fun setDataAtFragment(fragment: Fragment, userId: String) {
        val bundle = Bundle()
        bundle.putString("userId", userId)

        fragment.arguments = bundle
        // setFragment(fragment)
    }

    fun setDataAtFragment2(fragment: Fragment, userId: String) {
        val bundle = Bundle()
        bundle.putString("userId", userId)

        fragment.arguments = bundle
        // setFragment(fragment)
    }

    //데이터가 셋팅된 프래그먼트 띄우기
//    fun setFragment(fragment: Fragment) {
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.main_frm, fragment)
//        transaction.commit()
//    }

//    private fun sendData(userId: String) {
//        var pref = this.getPreferences(0)
//        var editor = pref?.edit()
//
//        editor?.putString("userId", userId)?.apply()
//    }
}