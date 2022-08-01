package com.example.o_ttae.ui.login

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.o_ttae.data.db.DBManager
import com.example.o_ttae.ui.MainActivity
import com.example.o_ttae.R
import com.example.o_ttae.ui.register.RegisterPageActivity

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

        // 로그인 버튼 누를 시
        loginBtn.setOnClickListener {
            // db
            dbManager = DBManager(this, "user", null, 1)
            sqlitedbR = dbManager.readableDatabase

            var cursor: Cursor
            var userEmail: String = loginEmail.text.toString()
            var passwordS: String = loginPassword.text.toString()

            // 이메일과 비밀번호가 일치하는지 체크
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

                // 메인화면으로 전환
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        // 회원가입 버튼 누를 시
        loginToRegBtn.setOnClickListener {
            loginMismatchTv.visibility = View.INVISIBLE
            loginNotExEmailTv.visibility = View.INVISIBLE

            // 회원가입 화면으로 전환
            startActivity(Intent(this, RegisterPageActivity::class.java))
        }
    }

    fun availableLogin(cursor: Cursor): Boolean {
        if (cursor.count > 0)  // 아이디 존재
        {
            while (cursor.moveToNext()) {
                sendUserName(cursor.getString((cursor.getColumnIndex("name"))).toString())
            }

            return true

        } else {
            // 가입된 적이 없는 이메일이라고 메시지 띄우기
            loginMismatchTv.visibility = View.VISIBLE
            loginMismatchTv.text = "존재하지 않거나 비밀번호가 틀렸습니다."

            return false
        }
    }

    // 로그인한 사용자 이름 저장
    private fun sendUserName(name: String) {
        var pref = applicationContext.getSharedPreferences("name", 0)
        var editor = pref?.edit()

        editor?.putString("name", name)?.apply()
    }
}