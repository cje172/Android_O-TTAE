package com.example.chapter5_5

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginPageActivity : AppCompatActivity() {


    lateinit var loginEmail: EditText
    lateinit var loginPassword: EditText
    lateinit var dbManager: DBManager
    lateinit var sqlitedbR: SQLiteDatabase
    lateinit var loginBtn: Button
    lateinit var loginToRegBtn: Button
    lateinit var loginNotExEmailTv: TextView
    lateinit var loginMismatchTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        loginEmail = findViewById(R.id.login_id_et)
        loginPassword = findViewById(R.id.login_password_et)
        loginBtn = findViewById(R.id.login_btn)
        loginToRegBtn = findViewById(R.id.login_to_reg_btn)
        loginNotExEmailTv = findViewById(R.id.login_not_ex_email_tv)
        loginMismatchTv = findViewById(R.id.login_mismatch_tv)

        // db

        // var userEmail:String=logIn_email.text.toString()
        // cursor =sqlitedbR.rawQuery("SELECT * FROM user WHERE email='$userEmail';",null)
        // 이메일이 db에 존재하는지 체크
        // 이메일과 비밀번호가 일치하는지 체크
        // 메인화면으로 전환
        loginBtn.setOnClickListener {
            dbManager = DBManager(this, "user", null, 1)
            sqlitedbR = dbManager.readableDatabase

            var cursor: Cursor
            var userEmail: String = loginEmail.text.toString()
            var passwordS: String = loginPassword.text.toString()
            cursor = sqlitedbR.rawQuery(
                "SELECT * FROM user WHERE email='$userEmail' and password='$passwordS';",
                null
            )
            if (availableLogin(cursor)) {
                loginMismatchTv.visibility = View.INVISIBLE
                loginNotExEmailTv.visibility = View.INVISIBLE

                cursor.close()
                sqlitedbR.close()
                dbManager.close()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        // 회원가입 화면으로 전환
        loginToRegBtn.setOnClickListener {
            loginMismatchTv.visibility = View.INVISIBLE
            loginNotExEmailTv.visibility = View.INVISIBLE
            startActivity(Intent(this, RegisterPageActivity::class.java))
        }
    }

    fun availableLogin(cursor: Cursor): Boolean {
        if (cursor.count > 0)  // 아이디 존재
        {
            while (cursor.moveToNext()) {
                sendUserName(cursor.getString((cursor.getColumnIndex("name"))).toString())
            }
//            var cursor2: Cursor
//            cursor2 =sqlitedbR.rawQuery("SELECT * FROM user;",null)
//            notExEmailText.visibility=View.VISIBLE
//            notExEmailText.text=cursor2.count.toString()
//            notExEmailText.visibility=View.INVISIBLE
//            misMatchText.visibility=View.VISIBLE
//            // password_mismatch.

            // var passwordTemp:String=cursor2.getString((cursor.getColumnIndex("password"))).toString()
            //  notExEmailText.visibility=View.VISIBLE
            // notExEmailText.text=emailTemp
            return true
//
//            if(logIn_password.text.toString().equals(passwordTemp))
//                return true
//            else
//            {
//                //아이디는 존재 비밀번호 일치 x
//                //이메일과 비밀번호가 일치하지 않습니다.
//                    notExEmailText.visibility=View.INVISIBLE
//                    misMatchText.visibility=View.VISIBLE
//                return false
//            }
        } else {
            // 가입된 적이 없는 이메일이라고 메시지 띄우기
            loginMismatchTv.visibility = View.VISIBLE
            loginMismatchTv.text = "존재하지 않거나 비밀번호가 틀렸습니다."

            return false
        }
    }

    private fun sendUserName(name: String) {
        var pref = applicationContext.getSharedPreferences("name", 0)
        var editor = pref?.edit()

        editor?.putString("name", name)?.apply()
    }
}