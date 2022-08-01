package com.example.o_ttae.ui.register

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.example.o_ttae.data.db.DBManager
import com.example.o_ttae.R
import com.example.o_ttae.ui.login.LoginPageActivity

class RegisterPageActivity : AppCompatActivity() {

    lateinit var registerBtn: Button
    lateinit var registerName: EditText
    lateinit var registerEmail: EditText
    lateinit var registerPassword: EditText
    lateinit var registerRePassword: EditText
    lateinit var registerPasswordMismatch: TextView
    lateinit var registerAllAgreeCb: CheckBox
    lateinit var registerFirstCb: CheckBox
    lateinit var registerSecondCb: CheckBox
    lateinit var registerThirdCb: CheckBox
    lateinit var registerChoiceCb: CheckBox

    // db
    lateinit var dbManager: DBManager
    lateinit var sqlitedbW: SQLiteDatabase
    lateinit var sqlitedbR: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

        registerBtn = findViewById(R.id.register_btn)
        registerName = findViewById(R.id.register_name_et)
        registerEmail = findViewById(R.id.register_email_et)
        registerPassword = findViewById(R.id.register_password_et)
        registerRePassword = findViewById(R.id.register_re_password_et)
        registerPasswordMismatch = findViewById(R.id.register_password_mismatch_tv)
        registerAllAgreeCb = findViewById(R.id.register_all_agree_cb)
        registerFirstCb = findViewById(R.id.register_first_cb)
        registerSecondCb = findViewById(R.id.register_second_cb)
        registerThirdCb = findViewById(R.id.register_third_cb)
        registerChoiceCb = findViewById(R.id.register_choice_cb)

        // db
        dbManager = DBManager(this, "user", null, 1)

        sqlitedbR = dbManager.readableDatabase  // 읽기
        sqlitedbW = dbManager.writableDatabase  // 쓰기

        // 회원가입 버튼 누를 시
        registerBtn.setOnClickListener {
            // 아이디 중복 & 이메일 중복 & 비밀번호 일치 확인
            if (checkId() && checkEmail() && checkPassword()) {
                // db에 정보 저장
                sqlitedbW.execSQL("INSERT INTO user VALUES('" + registerName.text.toString() + "','" + registerEmail.text.toString() + "','" + registerPassword.text.toString() + "');")
                // 로그인 화면으로 이동
                startActivity(Intent(this, LoginPageActivity::class.java))
            }
        }

        // 전체 동의 체크박스 클릭
        registerAllAgreeCb.setOnClickListener {
            if (registerAllAgreeCb.isChecked) {
                registerFirstCb.isChecked = true
                registerSecondCb.isChecked = true
                registerThirdCb.isChecked = true
                registerChoiceCb.isChecked = true
            } else {
                registerAllAgreeCb.isChecked = false
                registerFirstCb.isChecked = false
                registerSecondCb.isChecked = false
                registerThirdCb.isChecked = false
                registerChoiceCb.isChecked = false
            }

        }

    }

    // 아이디 중복 확인
    private fun checkId(): Boolean {
        var cursor: Cursor
        var userId: String = registerName.text.toString()
        cursor = sqlitedbR.rawQuery("SELECT * FROM user WHERE name='$userId';", null)

        if (cursor.count > 0)  // 중복 있음
        {
            registerPasswordMismatch.visibility = View.VISIBLE
            registerPasswordMismatch.text = "이미 있는 아이디 입니다."
            registerName.hint = "이미 있는 아이디 입니다."

            return false
        }
        return true
    }

    // 이메일 중복 확인
    private fun checkEmail(): Boolean {
        var cursor: Cursor
        var userEmail: String = registerEmail.text.toString()
        cursor = sqlitedbR.rawQuery("SELECT * FROM user WHERE email='$userEmail';", null)

        if (cursor.count > 0)  // 중복 있음
        {
            registerEmail.hint = "이미 가입된 이메일 입니다."

            return false
        }
        return true
    }

    // 비밀번호 일치 확인
    private fun checkPassword(): Boolean {
        var password: String = registerPassword.text.toString()
        var password2: String = registerRePassword.text.toString()

        if (!password.equals(password2)) {
            registerPasswordMismatch.visibility = View.VISIBLE

            return false
        } else {
            registerPasswordMismatch.visibility = View.INVISIBLE

            return true
        }
        return true
    }
}